package edu.hrbeu.chapterintent.demo3

import android.os.Bundle
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext

class CommunicationActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			MaterialTheme {
				MainScreen()
			}
		}
	}
}

@Composable
fun MainScreen() {
	var inputText by remember { mutableStateOf("") }
	var text by remember { mutableStateOf("") }
	val context = LocalContext.current
	// 启动Activity的Launcher
	val launcherSubActivity = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.resultCode == RESULT_OK) {
			val data: String? = result.data?.dataString
			text = data ?: "No Data"
		}
	}


	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = text, style = MaterialTheme.typography.bodyLarge)
		Spacer(modifier = Modifier.height(16.dp))
		Button(onClick = {

			val intent = Intent(context, SubActivity::class.java)
			intent.putExtra("data",inputText)
			launcherSubActivity.launch(intent)
		}) {
			Text("启动SubActivity")
		}
		Spacer(modifier = Modifier.height(8.dp))
		TextField(
			value = inputText,
			onValueChange = { inputText = it },
			label = { Text("传递给SubActivity的数据") },
			modifier = Modifier.fillMaxWidth()
		)
	}
}