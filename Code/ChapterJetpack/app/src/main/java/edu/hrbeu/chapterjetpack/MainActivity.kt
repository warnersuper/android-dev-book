package edu.hrbeu.chapterjetpack

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
import edu.hrbeu.chapterjetpack.demo1.NavHostActivity
import edu.hrbeu.chapterjetpack.demo2.ModelViewCounterActivity
import edu.hrbeu.chapterjetpack.demo3.KtorClientActivity
import edu.hrbeu.chapterjetpack.demo4.StateFlowCounterActivity
import edu.hrbeu.chapterjetpack.demo5.LifecycleScopeActivity
import edu.hrbeu.chapterjetpack.demo6.ViewModelScopeActivity
import edu.hrbeu.chapterjetpack.ui.theme.ChapterJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Text("点击下面按钮，启动示例")
        Button(onClick = {
            val intent = Intent(context, NavHostActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo1: Nav Host Demo")
        }

        Button(onClick = {
            val intent = Intent(context, ModelViewCounterActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo2: ModelView Counter Demo")
        }

        Button(onClick = {
            val intent = Intent(context, KtorClientActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo3: Ktor Client Demo")
        }

        Button(onClick = {
            val intent = Intent(context, StateFlowCounterActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo4: StateFlow Counter Demo")
        }

        Button(onClick = {
            val intent = Intent(context, LifecycleScopeActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo5: LifecycleScope Demo")
        }

        Button(onClick = {
            val intent = Intent(context, ViewModelScopeActivity::class.java)
            context.startActivity(intent)
        }){
            Text("Demo6: ViewModelScope Demo")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ChapterJetpackTheme {
        MainScreen()
    }
}