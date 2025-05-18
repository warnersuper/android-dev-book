package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text


class MainActivity : ComponentActivity() {

    private val TAG = "LIFTCYCLE"
    private val KEY = "MyKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          Log.i(TAG, "(1) onCreate()")

        setContent {
            Column {
                Text(text = "")
                Text(text = "Hello Activity Life Cycle")
                Button(onClick = {
                    finish()
                }) {
                    Text("结束程序")
                }
            }
        }

    }

    // 可视生命周期开始时被调用，对用户界面进行必要的更改
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "(2) onStart()")
    }

    // 在onStart()后被调用，用于恢复onSaveInstanceState()保存的用户界面信息
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "(3) onRestoreInstanceState()")
        val savedValue = savedInstanceState.getString(KEY) // 恢复保存的数据
        Log.i(TAG, "恢复数据，key:$KEY, value:$savedValue")
    }

    // 在活动生命周期开始时被调用，恢复被onPause()停止的用于界面更新的资源
    override fun onResume() {
        super.onResume()
        Log.i(TAG, "(4) onResume()")
    }

    // 在onPause()后被调用，保存界面临时信息
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "(5) onSaveInstanceState()")
        // 保存一些临时数据
        val value = "这里是保存的数据"
        savedInstanceState.putString(KEY,value)
        Log.i(TAG, "保存数据，key:$KEY, value:$value")
    }

    // 在重新进入可视生命周期前被调用，载入界面所需要的更改信息
    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "(6) onRestart()")
    }

    // 在活动生命周期结束时被调用，用来保存持久的数据或释放占用的资源
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "(7) onPause()")
    }

    // 在可视生命周期结束时被调用，用来释放占用的资源
    override fun onStop() {
        super.onStop()
        Log.i(TAG, "(8) onStop()")
    }

    // 在完全生命周期结束时被调用，释放资源，包括线程、数据连接等
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "(9) onDestroy()")
    }

}
