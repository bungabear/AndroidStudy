package com.bungabear.androidstudy.mediaprojection.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.view.Surface
import android.view.SurfaceView
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bungabear.androidstudy.R
import com.bungabear.androidstudy.mediaprojection.service.MediaProjectionService

class MediaProjectionActivityWithService : AppCompatActivity() {
    private val btnStart : Button by lazy { findViewById(R.id.btn_mediaprojection_start) }
    private val btnStop : Button by lazy { findViewById(R.id.btn_mediaprojection_stop) }
    private val surfaceView : SurfaceView by lazy { findViewById(R.id.sv_mediaprojection) }
    private var surface : Surface? = null
    private val mediaProjectionManager: MediaProjectionManager by lazy { getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager }
    private val mediaProjectionRequest : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
    ){
        when(it.resultCode){
            RESULT_OK -> {
                val data = it.data!!
                val surface = this.surface!!
                val intent = MediaProjectionService.startWithInitIntent(this, data, it.resultCode, surface)
                startService(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediaprojection)
        surface = surfaceView.holder.surface

        btnStart.setOnClickListener {
            startCapture()
        }

        btnStop.setOnClickListener {
            stopCapture()
        }
    }

    override fun onDestroy() {
        stopCapture()
        super.onDestroy()
    }

    private fun startCapture(){
        mediaProjectionRequest.launch(mediaProjectionManager.createScreenCaptureIntent())
    }

    private fun stopCapture(){
        val intent = MediaProjectionService.stopIntent(this)
        startService(intent)
    }

    override fun startService(intent: Intent): ComponentName? {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        }
        else {
            startService(intent)
        }
        return null
    }
}