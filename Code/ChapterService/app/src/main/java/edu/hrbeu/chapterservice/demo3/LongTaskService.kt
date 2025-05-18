package edu.hrbeu.chapterservice.demo3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LongTaskService : Service() {
    // 创建协程作用域
    private val serviceScope = CoroutineScope(Dispatchers.Default)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 启动一个后台协程模拟耗时任务
        serviceScope.launch {
            simulateLongTask()
            stopSelf()              // 任务完成后停止服务
        }
        return START_NOT_STICKY
    }

    private suspend fun simulateLongTask() {
        // 模拟耗时任务（如下载、计算等）
        for (i in 1..3) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@LongTaskService, "正在执行任务：第 $i 次输出", Toast.LENGTH_SHORT).show()
            }
            println("正在执行任务：第 $i 次输出")
            delay(3000) // 每3秒打印一次
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
        serviceScope.cancel()                   // 避免内存泄漏
    }

    override fun onBind(intent: Intent?): IBinder? = null
}