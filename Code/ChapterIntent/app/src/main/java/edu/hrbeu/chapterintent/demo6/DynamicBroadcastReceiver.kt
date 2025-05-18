package edu.hrbeu.chapterintent.demo6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DynamicBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val msg = intent.getStringExtra("message")
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}