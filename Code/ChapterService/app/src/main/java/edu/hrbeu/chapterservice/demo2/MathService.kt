package edu.hrbeu.chapterservice.demo2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MathService : Service() {
    private val mBinder: IBinder = LocalBinder()
    inner class LocalBinder : Binder() {
        val service: MathService
            get() = this@MathService
    }

    override fun onCreate() {
        Log.d("MathService", "创建：MathService")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("MathService", "绑定：MathService")
        return mBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.d("MathService", "重新绑定：MathService")
    }
    override fun onUnbind(intent: Intent): Boolean {
        Log.d("MathService", "解绑：MathService")
        return true
    }

    override fun onDestroy() {
        Log.d("MathService", "销毁：MathService")
    }


    fun Add(a: Long, b: Long): Long {
        return a + b
    }
}