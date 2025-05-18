package edu.hrbeu.androidui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun ModifierInteractiveDemo1() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White)
            .clickable {
                // 处理点击事件
                println("Box clicked!")
            }
    ) {
        Text(
            text = "Click Me",
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierInteractiveDemo2() {
    var message by remember { mutableStateOf("按下任意键") }

    Box(
        modifier = Modifier
            .size(150.dp)
            .background(Color.LightGray)
            .focusable() // 必须可聚焦
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    message = "你按下了: ${event.key}"
                }
                true // 表示事件已处理
            }
    ) {
        Text(
            text = message,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


