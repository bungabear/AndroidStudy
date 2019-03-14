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
    fun testFun(){
        Toast.makeText(this, "함수 호출 테스트", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    inner class ServiceBinder : Binder(){ val service = this@SimpleBindService}
}