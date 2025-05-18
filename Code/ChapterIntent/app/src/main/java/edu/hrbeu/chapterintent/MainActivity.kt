package edu.hrbeu.chapterintent

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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import edu.hrbeu.chapterintent.demo1.IntentDemoActivity
import edu.hrbeu.chapterintent.demo2.WebViewIntentDemoActivity
import edu.hrbeu.chapterintent.demo3.CommunicationActivity
import edu.hrbeu.chapterintent.demo4.IntentResolutionActivity
import edu.hrbeu.chapterintent.demo5.StaticBroadcastReceiverActivity
import edu.hrbeu.chapterintent.demo6.DynamicBroadcastReceiver
import edu.hrbeu.chapterintent.demo6.DynamicBroadcastReceiverActivity
import edu.hrbeu.chapterintent.ui.theme.ChapterIntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterIntentTheme {
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
            val intent = Intent(context, IntentDemoActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo1: Intent Demo")
        }

        Button(onClick = {
            val intent = Intent(context, WebViewIntentDemoActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo2: WebView Intent Demo")
        }

        Button(onClick = {
            val intent = Intent(context, CommunicationActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo3: Activity Communication")
        }

        Button(onClick = {
            val intent = Intent(context, IntentResolutionActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo4: Intent Resolution Demo")
        }

        Button(onClick = {
            val intent = Intent(context, StaticBroadcastReceiverActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo5: Static Broadcast Receiver Demo")
        }

        Button(onClick = {
            val intent = Intent(context, DynamicBroadcastReceiverActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo6: Dynamic Broadcast Receiver Demo")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DemoIndexPreview() {
    ChapterIntentTheme {
        DemoIndex()
    }
}