package com.bungabear.androidstudy.servicebind

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class SimpleBindService : Service(){
    private val TAG = this.javaClass.simpleName
    override fun onBind(p0: Intent?): IBinder {
        Log.d(TAG, "바인드됨")
        return ServiceBinder()
    }

    public var count = 0
    fun testFun(){
        Log.d(TAG, "테스트 함수 $count")
//        Toast.makeText(this, "함수 호출 테스트", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        Thread{
            Thread.sleep(3000)
            this@SimpleBindService.stopSelf()
        }.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "unbinded")
        return super.onUnbind(intent)
    }

    inner class ServiceBinder : Binder(){ val service = this@SimpleBindService}
}