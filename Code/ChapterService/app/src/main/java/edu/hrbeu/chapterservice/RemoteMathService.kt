package edu.hrbeu.chapterservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class RemoteMathService : Service() {

    private val mBinder = object : IMathService.Stub() {
        override fun EuclideanDivision(a: Int, b: Int): EResult {
            val quotient = a / b
            val remainder = a % b
            return EResult(quotient, remainder)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this, "远程绑定：MathService", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "取消远程绑定：MathService", Toast.LENGTH_SHORT).show()
        return false
    }
}