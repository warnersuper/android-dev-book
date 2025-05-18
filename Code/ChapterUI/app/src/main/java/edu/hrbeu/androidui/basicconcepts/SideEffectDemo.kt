package edu.hrbeu.androidui.basicconcepts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

//==================== 示例1 =============================
//@Preview
@Composable
fun UserProfile(userId: String) {
    var userName by remember { mutableStateOf("加载中...") }

    LaunchedEffect(userId) {
        // 当 userId 变化时重新执行此协程
        userName = loadUserName(userId)
    }

    Text(text = "用户名：$userName")
}

// 模拟网络请求
suspend fun loadUserName(id: String): String {
    delay(1000)
    return "用户$id"
}


//======================= 示例2 ================================
//@Preview
@Composable
fun TimerLoggerComposable() {
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            Log.d("TimerLogger", "定时任务触发")
        }
    }

    Text(text = "定时任务已启动，每秒触发一次")
}

//======================= 示例3 ================================
//@Preview
@Composable
fun DisposableScreen(){
    var show by remember { mutableStateOf(true) }

    Column {
        Button(onClick = { show = !show }) {
            Text("切换组件")
        }

        if (show) {
            SimpleDisposableEffect()
        }
    }

}


@Composable
fun SimpleDisposableEffect() {
    DisposableEffect(Unit) {
        Log.d("DisposableEffect", "注册资源")

        onDispose {
            Log.d("DisposableEffect", "释放资源")
        }
    }

    Text("观察日志输出")
}

//======================= 示例4 ================================
//@Preview
@Composable
fun SideEffectExample() {
    var count by remember { mutableStateOf(0) }
    SideEffect {
        Log.d("SideEffectExample", "当前计数值为：$count")
    }
    Column {
        Text(text = "计数：$count")
        Button(onClick = { count += 1}){
            Text(text = "增加count")
        }
    }

}


//======================= 示例5 ================================
//@Preview
@Composable
fun RememberCoroutineScopeExample() {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("请点击按钮") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            scope.launch {
                text = "正在处理中..."
                delay(2000)
                text = "处理完成！"
            }
        }) {
            Text("开始任务")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text)
    }
}


//======================= 示例6 ================================


//@Preview
@Composable
fun DerivedStateExample() {
    var text by remember { mutableStateOf("") }
    val textLength by remember {
        derivedStateOf { text.length }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("请输入文本") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("当前字数：$textLength")
    }
}



//======================= 示例7 ================================

//@Preview
@Composable
fun CountLoggerExample() {
    var count by remember { mutableStateOf(0) }
    val currentCount by rememberUpdatedState(count)
    val capturedCount = count
    LaunchedEffect(Unit) {
        delay(5000)
        Log.d("CountLogger", "[rememberUpdatedState] count：${currentCount}")
        Log.d("CountLogger", "[capturedCount] count：${capturedCount}")
    }
    Column {
        Text("当前count是$count")
        Button(onClick = { count +=1 }) {
            Text("增加count")
        }
    }

}



//======================= 示例8 ================================

//@Preview
@Composable
fun SnapshotFlowExample() {
    var text by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        snapshotFlow { text }
            .collectLatest { value ->
                Log.d("SnapshotFlow", "文本变化为：$value")
            }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("请输入内容：", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("输入") }
        )
    }
}
































