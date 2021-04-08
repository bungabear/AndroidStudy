package com.bungabear.androidstudy.mediaprojection.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.IBinder
import android.view.Surface
import androidx.lifecycle.MutableLiveData
import com.bungabear.androidstudy.R

class MediaProjectionService : Service() {

    companion object {
        const val ACTION_INIT = "ACTION_INIT"
        const val ACTION_START_INIT = "ACTION_START_INIT"
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"

        const val EXTRA_CODE = "EXTRA_CODE"
        const val EXTRA_DATA = "EXTRA_DATA"
        const val EXTRA_SURFACE = "EXTRA_SURFACE"

        fun startWithInitIntent(context: Context, data: Intent, code: Int, surface: Surface): Intent {
            return Intent(context, MediaProjectionService::class.java).apply {
                action = ACTION_START_INIT
                putExtra(EXTRA_CODE, code)
                putExtra(EXTRA_DATA, data)
                putExtra(EXTRA_SURFACE, surface)
            }
        }
        fun initIntent(context: Context, data: Intent, code: Int): Intent {
            return Intent(context, MediaProjectionService::class.java).apply {
                action = ACTION_INIT
                putExtra(EXTRA_CODE, code)
                putExtra(EXTRA_DATA, data)
            }
        }
        fun startIntent(context: Context, surface: Surface): Intent {
            return Intent(context, MediaProjectionService::class.java).apply {
                action = ACTION_START
                putExtra(EXTRA_SURFACE, surface)
            }
        }
        fun stopIntent(context: Context): Intent {
            return Intent(context, MediaProjectionService::class.java).apply {
                action = ACTION_STOP
            }
        }

        val state = MutableLiveData(ACTION_STOP)
    }

    private val mediaProjectionManager: MediaProjectionManager by lazy { getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager }
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTION_START_INIT ->
                if(state.value == ACTION_STOP){
                    init(intent)
                    startCapture(intent)
                }
                else {
                    stopSelf()
                }
            ACTION_INIT ->
                if(state.value == ACTION_STOP) {
                    init(intent)
                }
                else {
                    stopSelf()
                }
            ACTION_START ->
                if(state.value == ACTION_INIT) {
                    startCapture(intent)
                }
                else {
                    stopSelf()
                }
            ACTION_STOP ->
                if(state.value == ACTION_START) {
                    stopCapture()
                }
                else {
                    stopSelf()
                }
        }
        return START_REDELIVER_INTENT
    }

    fun init(intent: Intent){
        state.postValue(ACTION_INIT)
        val resultCode = intent.getIntExtra(EXTRA_CODE, Activity.RESULT_CANCELED)
        val data = intent.getParcelableExtra<Intent>(EXTRA_DATA)
        mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data)
        mediaProjection?.registerCallback(object: MediaProjection.Callback(){
            override fun onStop() {
                stopCapture()
                super.onStop()
            }
        }, null)
    }

    fun startCapture(intent: Intent){
        state.postValue(ACTION_START)
        val surface = intent.getParcelableExtra(EXTRA_SURFACE) as Surface
        virtualDisplay = createVirtualDisplay(surface)
    }

    fun createVirtualDisplay(surface: Surface): VirtualDisplay? {
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

    fun stopCapture(){
        state.postValue(ACTION_STOP)
        mediaProjection?.stop()
        virtualDisplay?.release()
        stopSelf()
    }

    fun startForegroundService() {
        val channelId = "MediaProjectionService"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                    channelId,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(serviceChannel)
            }
        }
        val notificationIntent = Intent(this, MediaProjectionService::class.java)
        val pendingIntent = PendingIntent.getActivity(
                this,
                0, notificationIntent, 0
        )

        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId)
        } else {
            Notification.Builder(this)
        }
                .setContentTitle("Foreground Service")
                .setSmallIcon(R.drawable.iv_vt_heart)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1000, notification)
    }
}