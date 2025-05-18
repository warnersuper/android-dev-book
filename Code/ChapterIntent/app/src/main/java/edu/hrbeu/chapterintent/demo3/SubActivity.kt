package edu.hrbeu.chapterintent.demo3


import android.os.Bundle
import android.net.Uri
import android.content.Intent
import androidx.activity.ComponentActivity
import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment // 添加此导入语句
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext


class SubActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                SubActivity1Screen()
            }
        }
    }
}

@Composable
fun SubActivity1Screen() {
    var inputText by remember { mutableStateOf("") }

    val context = LocalContext.current as ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("返回主Activity的数据") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                val resultIntent= Intent()
                resultIntent.data = Uri.parse(inputText)
                context.setResult(Activity.RESULT_OK, resultIntent)
                context.finish()
            }) {
                Text("接受")
            }
            Button(onClick = {
                context.setResult(Activity.RESULT_CANCELED)
                context.finish()
            }) {
                Text("撤销")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(context.intent.getStringExtra("data")?: "", style = MaterialTheme.typography.headlineSmall)
    }
}