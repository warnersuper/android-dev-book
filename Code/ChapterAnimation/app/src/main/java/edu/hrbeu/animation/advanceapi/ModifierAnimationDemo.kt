package edu.hrbeu.androidui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun ModifierAnimationDemo1() {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = if (expanded) "这是一段展开后的长文本，展示更多内容。" else "短文本",
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .animateContentSize(), // 关键修饰符
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (expanded) "点击收起" else "点击展开",
            color = Color.Blue
        )
    }
}


@Preview
@Composable
fun ModifierAnimationDemo2() {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "隐藏" else "显示")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = visible) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Cyan)
                    .animateEnterExit(
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Hello")
            }
        }
    }
}
