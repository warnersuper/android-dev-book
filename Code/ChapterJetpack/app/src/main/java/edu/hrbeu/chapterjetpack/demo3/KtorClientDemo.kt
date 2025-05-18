package edu.hrbeu.chapterjetpack.demo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.chapterjetpack.ui.theme.ChapterJetpackTheme

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
//import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.coroutines.launch


class KtorClientActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}


@Serializable
data class News(val id: Int, val title: String, val body: String)

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var newsTitle by remember { mutableStateOf("尚未加载，标题部分") }
    var newsBody by remember { mutableStateOf("尚未加载，主题部分") }
    var text by remember { mutableStateOf("https://jsonplaceholder.typicode.com/posts/1") }

    Column {
        Spacer(Modifier.height(50.dp))
        TextField( value = text,
            onValueChange = {
            text = it
        })
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val news: News =
                        client.get(text).body()
                    newsTitle = news.title
                    newsBody = news.body
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {
            Text("获取json数据")
        }

        Text(
            text = "$newsTitle",
            modifier = modifier
        )
        HorizontalDivider()
        Text(
            text = "$newsBody",
            modifier = modifier
        )
    }

}



