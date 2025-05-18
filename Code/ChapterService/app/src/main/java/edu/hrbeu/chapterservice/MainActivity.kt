package edu.hrbeu.chapterservice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import edu.hrbeu.chapterservice.demo1.SimpleRandomServiceActivity
import edu.hrbeu.chapterservice.demo2.SimpleMathServiceActivity
import edu.hrbeu.chapterservice.demo3.LongTaskServiceActivity
import edu.hrbeu.chapterservice.demo4.RemoteMathServiceActivity
import edu.hrbeu.chapterservice.demo5.RemoteMathCallerActivity
import edu.hrbeu.chapterservice.ui.theme.ChapterServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterServiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoIndex(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DemoIndex(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Text("点击下面按钮，启动示例")
        Button(onClick = {
            val intent = Intent(context, SimpleRandomServiceActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo1: Simple Random Service")
        }

        Button(onClick = {
            val intent = Intent(context, SimpleMathServiceActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo2: Simple Math Service")
        }

        Button(onClick = {
            val intent = Intent(context, LongTaskServiceActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo3: Long Task Service")
        }

        Button(onClick = {
            val intent = Intent(context, RemoteMathServiceActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo4: Remote Math Service")
        }

        Button(onClick = {
            val intent = Intent(context, RemoteMathCallerActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo5: Remote Math Caller")
        }
//
//        Button(onClick = {
//            val intent = Intent(context, DynamicBroadcastReceiverActivity::class.java)
//            context.startActivity(intent)
//        }){
//            Text("Demo6: Dynamic Broadcast Receiver Demo")
//        }
    }

}


@Preview(showBackground = true)
@Composable
fun DemoIndexPreview() {
    ChapterServiceTheme {
        DemoIndex()
    }
}