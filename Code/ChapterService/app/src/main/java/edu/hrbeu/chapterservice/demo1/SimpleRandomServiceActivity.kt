package edu.hrbeu.chapterservice.demo1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview


class SimpleRandomServiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serviceIntent = Intent(this, RandomService::class.java)

        setContent {
            MainScreen(
                onStartClick = { startService( serviceIntent) },
                onStopClick = { stopService(serviceIntent) }
            )
        }
    }
}

@Composable
fun MainScreen(onStartClick: () -> Unit, onStopClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello, SimpleRandomService!",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(onClick = onStartClick) {
                Text(text = "启动Service")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onStopClick) {
                Text(text = "停止Service")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(
        onStartClick = {},
        onStopClick = {}
    )
}