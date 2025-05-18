package edu.hrbeu.animation.specification

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
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
fun SnapDemo1() {
    var isRed by remember { mutableStateOf(true) }

    val color by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.Green,
        animationSpec = if (isRed)
            snap()
        else
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            ),
        label = "snapColor"
    )

    Box(
        modifier = Modifier
            .size(150.dp)
            .background(color)
            .clickable { isRed = !isRed },
        contentAlignment = Alignment.Center
    ) {
        Text("Click Me", color = Color.White)
    }
}