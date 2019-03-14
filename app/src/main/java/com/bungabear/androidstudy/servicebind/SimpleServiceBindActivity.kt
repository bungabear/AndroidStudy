package com.bungabear.androidstudy.servicebind

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity

class SimpleServiceBindActivity : AppCompatActivity(){

    private var mSimpleBindService : SimpleBindService? = null
    private val mSimpleBindServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as SimpleBindService.ServiceBinder
            mSimpleBindService = binder.service
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            mSimpleBindService = null
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 서비스 바인드
        val bindIntnet = Intent(this, SimpleBindService::class.java)
        bindService(bindIntnet, mSimpleBindServiceConnection, Service.BIND_AUTO_CREATE)

        // 서비스 내의 함수 호출 가능(비동기 주의)
        mSimpleBindService?.testFun()
    }

    override fun onDestroy() {
        // TODO startService없이 unbind시 서비스 종료되는지 확인
        unbindService(mSimpleBindServiceConnection)
        super.onDestroy()
    }
}