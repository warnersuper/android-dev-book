package edu.hrbeu.chapterintent.demo5

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


class StaticBroadcastReceiverActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen();
            }
        }
    }

}

@Preview
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
        Text(text = "Static Broadcast Receiver")
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
                val intent = Intent(context, StaticBroadcastReceiver::class.java)
                intent.putExtra("message", inputText)
                context.sendBroadcast(intent)
            },
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
        ) {
            Text("发送广播消息")
        }
    }


}
