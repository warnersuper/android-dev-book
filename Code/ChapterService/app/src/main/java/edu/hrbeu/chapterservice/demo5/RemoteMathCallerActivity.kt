package edu.hrbeu.chapterservice.demo5

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.hrbeu.chapterservice.IMathService

class RemoteMathCallerActivity : ComponentActivity() {

	private var mathService: IMathService? = null
	private var isBound = false

	private val mConnection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
			mathService = IMathService.Stub.asInterface(service)
			Toast.makeText(this@RemoteMathCallerActivity, "绑定成功", Toast.LENGTH_SHORT).show()
		}

		override fun onServiceDisconnected(name: ComponentName?) {
			mathService = null
			Toast.makeText(this@RemoteMathCallerActivity, "服务断开", Toast.LENGTH_SHORT).show()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


		setContent {
			RemoteMathCallerDemoUI()
		}
	}

	@Composable
	fun RemoteMathCallerDemoUI() {
		var result by remember { mutableStateOf("") }

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(text = result, style = MaterialTheme.typography.headlineMedium)
			Spacer(modifier = Modifier.height(16.dp))
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceEvenly
			) {
				Button(onClick = {
					if (!isBound) {
						val intent = Intent()
//						intent.action = "edu.hrbeu.chapterservice.REMOTE_MATH" // 对应服务端 action
//						intent.setPackage("edu.hrbeu.chapterservice") // 服务端 App 的包名
						intent.setClassName("edu.hrbeu.chapterservice", "edu.hrbeu.chapterservice.RemoteMathService")
						bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

						isBound = true
					}
				}) {
					Text("远程服务绑定")
				}
				Button(onClick = {
					if (isBound) {
						unbindService(mConnection)
						mathService = null
						isBound = false
					}
				}) {
					Text("取消服务绑定")
				}
			}
			Spacer(modifier = Modifier.height(16.dp))
			Button(onClick = {
				if (mathService == null) {
					result = "未绑定远程服务"
					return@Button
				}
				val a = (Math.random() * 100).toInt()
				val b = (Math.random() * 20).toInt() + 1
				try {
					val eResult = mathService?.EuclideanDivision(a, b)
					result = "$a 除以 $b  商${eResult?.quotient} 余${eResult?.remainder}"
				} catch (e: RemoteException) {
					result = "远程调用失败：${e.message}"
				}
			}) {
				Text("欧几里得除法")
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		if (isBound) {
			unbindService(mConnection)
			isBound = false
		}
	}
}