package edu.hrbeu.chapterservice.demo2

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.content.ServiceConnection
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.util.Log
import edu.hrbeu.chapterservice.demo2.MathService.LocalBinder

class SimpleMathServiceActivity : ComponentActivity() {
    private var mathService: MathService? = null
    private var isBound = false

    // 使用 MutableState 定义可变的状态变量
    private val serviceStatus = mutableStateOf("未绑定服务")

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: android.os.IBinder) {
            mathService = (service as LocalBinder).service
            Log.d("MainActivity", "Service bound successfully.")
            serviceStatus.value = "服务已绑定" // 更新服务状态
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mathService = null
            Log.d("MainActivity", "Service unbound.")
            serviceStatus.value = "未绑定服务" // 更新服务状态
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                mathService = mathService,
                onBindClick = {
                    if (!isBound) {
                        val serviceIntent = Intent(this, MathService::class.java)
                        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE)
                        isBound = true
                        Log.d("MainActivity", "Attempting to bind service...")
                        serviceStatus.value = "正在绑定服务..." // 更新服务状态
                    }

                },
                onUnbindClick = {
                    if (isBound) {
                        unbindService(mConnection)
                        isBound = false
                        Log.d("MainActivity", "Attempting to unbind service...")
                        serviceStatus.value = "未绑定服务" // 更新服务状态
                    }
                },
                onComputeClick = {
                    if (mathService == null) {
                        "未绑定服务"
                    } else {
                        try {
                            val a = Math.round(Math.random() * 100)
                            val b = Math.round(Math.random() * 100)
                            val result = mathService?.Add(a, b)
                            "$a + $b = $result"
                        } catch (e: Exception) {
                            Log.e("MainActivity", "计算错误", e)
                            "计算错误：${e.message}"
                        }
                    }
                },
                serviceStatus = serviceStatus
            )
        }
    }
}

// 自定义Composable函数
@Composable
fun MainScreen(
    mathService: MathService?,
    onBindClick: () -> Unit,
    onUnbindClick: () -> Unit,
    onComputeClick: () -> String,
    serviceStatus: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = serviceStatus.value, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBindClick) {
            Text("绑定服务")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onUnbindClick) {
            Text("解绑服务")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            serviceStatus.value = onComputeClick()
        }) {
            Text("加法运算")
        }
    }
}