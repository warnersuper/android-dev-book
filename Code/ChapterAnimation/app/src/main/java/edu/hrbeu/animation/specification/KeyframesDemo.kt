package edu.hrbeu.animation.specification

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun KeyframesDemo1() {
    var visible by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = keyframes {
            durationMillis = 1000
            1f at 0                    // 起始：完全可见
            0.7f at 300               // 300ms 后降低一些
            0.3f at 600                 // 渐变到更低
            0f at 1000                // 完全不可见
        }
    )

    Box(
        modifier = Modifier
            .size(150.dp)
            .background(if (!visible) Color.Blue.copy(alpha = alpha) else Color.Blue )
            .clickable { visible = !visible },
        contentAlignment = Alignment.Center
    ) {
        Text("Click", color = if (visible) Color.White else Color.Black )
    }
}
