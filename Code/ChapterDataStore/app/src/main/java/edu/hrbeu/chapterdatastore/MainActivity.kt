package edu.hrbeu.chapterdatastore

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
import edu.hrbeu.chapterdatastore.demo1.SimplePreferenceActivity
import edu.hrbeu.chapterdatastore.demo2.InternalFileActivity
import edu.hrbeu.chapterdatastore.demo3.ResourceFileActivity
import edu.hrbeu.chapterdatastore.demo4.SQLiteActivity
import edu.hrbeu.chapterdatastore.demo5.ContentResolverActivity
import edu.hrbeu.chapterdatastore.ui.theme.ChapterDataStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterDataStoreTheme {
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
            val intent = Intent(context, SimplePreferenceActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo1: Simple Preference")
        }

        Button(onClick = {
            val intent = Intent(context, InternalFileActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo2: Internal File")
        }

        Button(onClick = {
            val intent = Intent(context, ResourceFileActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo3: Resource File")
        }

        Button(onClick = {
            val intent = Intent(context, SQLiteActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo4: SQLlite")
        }

        Button(onClick = {
            val intent = Intent(context, ContentResolverActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo5: Data Share")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DemoIndexPreview() {
    ChapterDataStoreTheme {
        DemoIndex()
    }
}