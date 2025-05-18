package edu.hrbeu.chapterintent.demo6

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class DynamicBroadcastReceiverActivity: ComponentActivity() {
    private lateinit var myReceiver: DynamicBroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 1. 创建 BroadcastReceiver 实例
        myReceiver = DynamicBroadcastReceiver()

        // 2. 创建 IntentFilter 监听指定 Action
        val filter = IntentFilter("edu.hrbeu.BroadcastReceiverDemo2.ACTION_CUSTOM")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android 14及以上版本，必须指定flags
                // RECEIVER_EXPORTED 表示可以接收应用外部广播
                // RECEIVER_NOT_EXPORTED 表示仅接收应用内部广播
                registerReceiver(myReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
            // 旧版本Android
            registerReceiver(myReceiver, filter)
        }

        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 4. 取消注册广播，防止内存泄漏
        unregisterReceiver(myReceiver)

    }
}

@Composable
fun MainScreen() {
    var inputText by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Dynamic Broadcast Receiver")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter message") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                println("OnClick")
                val intent = Intent("edu.hrbeu.BroadcastReceiverDemo2.ACTION_CUSTOM")
                intent.putExtra("message", inputText)
                context.sendBroadcast(intent)
                println("OnClick Over")
            },
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
        ) {
            Text("发送广播消息")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        MainScreen()
    }
}