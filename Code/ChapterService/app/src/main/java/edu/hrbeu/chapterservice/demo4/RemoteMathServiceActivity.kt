package edu.hrbeu.chapterservice.demo4

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.hrbeu.chapterservice.IMathService

class RemoteMathServiceActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Remote Math Service", style = MaterialTheme.typography.headlineMedium)

        Text(text = "intent-filter:edu.hrbeu.chapterservice.REMOTE_MATH")
    }
}

