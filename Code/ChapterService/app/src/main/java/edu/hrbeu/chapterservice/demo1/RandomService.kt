package edu.hrbeu.chapterservice.demo1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class RandomService : Service() {

	override fun onCreate() {
		super.onCreate()
		Toast.makeText(this, "(1) 调用onCreate()", Toast.LENGTH_LONG).show()
	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Toast.makeText(this, "(2) 调用onStartCommand()", Toast.LENGTH_SHORT).show()

		val randomDouble = Math.random()
		val msg = "随机数：$randomDouble"
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

		return START_NOT_STICKY
	}

	override fun onDestroy() {
		super.onDestroy()
		Toast.makeText(this, "(3) 调用onDestroy()", Toast.LENGTH_SHORT).show()
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}
}