package edu.hrbeu.animation.specification

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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


@Preview
@Composable
fun RepeatableDemo1() {
    var startAnimation by remember { mutableStateOf(false) }

    // 使用 animateDpAsState + repeatable 动画规范
    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 200.dp else 0.dp,
        animationSpec = repeatable(
            iterations = 4, // 移动 4 次
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Restart
        ),
        label = "boxOffset"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .offset(x = if (startAnimation) offsetX else 0.dp)
                .size(80.dp)
                .background(Color.Green)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { startAnimation = !startAnimation }) {
            Text("开始动画")
        }
    }
}