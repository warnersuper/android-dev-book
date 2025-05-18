package edu.hrbeu.animation.baseapi

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch



@Preview
@Composable
fun AnimatableDemo1() {
    val animatable = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(animatable.value.toInt(), 0) }
                .size(100.dp)
                .background(Color.Red)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                scope.launch {
                    if (animatable.isRunning) {
                        animatable.stop() // 如果在动，就停止
                    } else {
                        animatable.animateTo(400f) // 否则移动到 400
                    }
                }
            }) {
                Text("Animate or Stop")
            }

            Button(onClick = {
                scope.launch {
                    animatable.snapTo(0f) // 立刻回到原点
                }
            }) {
                Text("Snap to Start")
            }
        }

        Text(text = "Velocity: %.2f".format(animatable.velocity))
        Text(text = "Is Running: ${animatable.isRunning}")
    }
}

@Preview
@Composable
fun AnimatableDemo2() {
    val scope = rememberCoroutineScope()
    val offsetY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scope.launch {
            // 从当前位置以初始速度4000f开始惯性滑动
            offsetY.animateDecay(
                initialVelocity = 4000f,
                animationSpec = exponentialDecay()
            )
        }
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(0, offsetY.value.toInt()) }
            .size(100.dp)
            .background(Color.Red)
    )
}

