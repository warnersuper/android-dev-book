package edu.hrbeu.chapterjetpack.demo6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import edu.hrbeu.chapterjetpack.ui.theme.ChapterJetpackTheme


class ViewModelScopeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterJetpackTheme {
                UserScreen()
            }
        }
    }
}

@Preview
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val userInfo by viewModel.userInfo.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserInfo("warnersuper")
    }

    userInfo?.let { user ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text("用户名：${user.login}")
            Text("用户ID：${user.id}")
            Text("创建时间：${user.created_at}")
            Text("更新实际：${user.updated_at}")

            Image(
                painter = rememberAsyncImagePainter(user.avatar_url),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
        }
    } ?: Text("加载中...")
}






