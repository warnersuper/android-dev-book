package edu.hrbeu.chapterjetpack.demo5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import edu.hrbeu.chapterjetpack.demo6.UserViewModel
import edu.hrbeu.chapterjetpack.ui.theme.ChapterJetpackTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class LifecycleScopeActivity : ComponentActivity() {
    // 模拟一个流式的位置提供器
    private val locationFlow = flow {
        while (true) {
            delay(5000) // 每 5 秒获取一次
            emit(getFakeLocation()) // 模拟位置
        }
    }

    private var latestLocation by mutableStateOf("正在获取位置...")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 使用 lifecycleScope 收集位置变化
        lifecycleScope.launch {
            locationFlow.collect { location ->
                latestLocation = location
            }
        }

        setContent {
            Box(Modifier.padding(100.dp)) {
                Text(latestLocation)
            }
        }
    }

    // 模拟获取位置
    private fun getFakeLocation(): String {
        val lat = (Math.random() * 180 - 90).toFloat()
        val lon = (Math.random() * 360 - 180).toFloat()
        return "Lat: %.2f, Lon: %.2f".format(lat, lon)
    }
}





