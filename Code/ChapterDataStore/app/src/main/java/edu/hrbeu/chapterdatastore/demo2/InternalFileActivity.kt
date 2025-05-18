package edu.hrbeu.chapterdatastore.demo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.IOException

class InternalFileActivity : ComponentActivity() {

	private val FILE_NAME = "fileDemo.txt"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			InternalFileDemoApp()
		}
	}

	@Composable
	fun InternalFileDemoApp() {
		var text by remember { mutableStateOf("") }
		var displayText by remember { mutableStateOf("") }
		var label by remember { mutableStateOf("") }
		var appendMode by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,  // 垂直居中
            horizontalAlignment = Alignment.Companion.CenterHorizontally  // 水平居中
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("输入内容") },
                modifier = Modifier.Companion.fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { writeToFile(text, appendMode); text = "" }) {
                    Text("写入文件")
                }
                Button(onClick = {
                    displayText = readFromFile(); label =
                    "文件读取成功，文件长度：${displayText.length}"
                }) {
                    Text("读取文件")
                }
            }
            Checkbox(
                checked = appendMode,
                onCheckedChange = { appendMode = it },
                modifier = Modifier.Companion.padding(end = 8.dp)
            )
            Text("追加模式", style = MaterialTheme.typography.bodySmall)
            Text(displayText, style = MaterialTheme.typography.bodyMedium)
        }
	}

	private fun writeToFile(text: String, appendMode: Boolean) {
		var mode = if (appendMode) MODE_APPEND else MODE_PRIVATE
		try {
			openFileOutput(FILE_NAME, mode).use { fos ->
				fos.write(text.toByteArray())
			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	private fun readFromFile(): String {
		return try {
			openFileInput(FILE_NAME).use { fis ->
				if (fis.available() == 0) {
					return ""
				}
				val bytes = ByteArray(fis.available())
				fis.read(bytes)
				String(bytes)
			}

		} catch (e: IOException) {
			e.printStackTrace()
			""
		}
	}
}