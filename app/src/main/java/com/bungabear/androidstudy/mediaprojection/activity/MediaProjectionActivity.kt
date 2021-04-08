package com.bungabear.androidstudy.mediaprojection.activity

import android.app.Activity
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
import com.bungabear.androidstudy.R


/**
 * @see (https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/media/projection/MediaProjectionDemo.java)
 */
class MediaProjectionActivity : Activity() {
    private val btnStart : Button by lazy { findViewById(R.id.btn_mediaprojection_start) }
    private val btnStop : Button by lazy { findViewById(R.id.btn_mediaprojection_stop) }
    private val surfaceView : SurfaceView by lazy { findViewById(R.id.sv_mediaprojection) }
    private var surface : Surface? = null
    private var mediaProjectionManager: MediaProjectionManager? = null
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediaprojection)

        surface = surfaceView.holder.surface
        mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager


        btnStart.setOnClickListener {
            startCapture()
        }

        btnStop.setOnClickListener {
            stopCapture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            PERMISSION_CODE -> {
                when (resultCode) {
                    RESULT_OK -> {
                        mediaProjection = mediaProjectionManager?.getMediaProjection(resultCode, data)
                        mediaProjection?.registerCallback(
                                object: MediaProjection.Callback() {
                                    override fun onStop() {
                                        stopCapture()
                                        super.onStop()
                                    }
                                },
                                null
                        )
                        virtualDisplay = createVirtualDisplay()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        stopCapture()
        super.onDestroy()
    }

    private fun startCapture(){
        if(mediaProjection == null){
//            val result : ActivityResultLauncher = registerFor
            startActivityForResult(mediaProjectionManager?.createScreenCaptureIntent(), PERMISSION_CODE)
//            startActivityForResult(mediaProjectionManager?.createScreenCaptureIntent(), PERMISSION_CODE)
        }

        virtualDisplay = createVirtualDisplay()
    }

    private fun stopCapture(){
        virtualDisplay?.release()
        virtualDisplay = null
//        mediaProjection = null
    }

    private fun createVirtualDisplay(): VirtualDisplay? {
        return mediaProjection?.createVirtualDisplay(
                "MediaProjectionDemo",
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                surface,
                null,
                null
        )
    }

    companion object{
        const val PERMISSION_CODE = 1000
    }
}