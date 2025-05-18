package edu.hrbeu.animation.gesture

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@Preview
@Composable
fun PointerInputDemo1() {
    var message by remember { mutableStateOf("点击检测单击、双击和长按") }

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { message = "单击" },
                    onDoubleTap = { message = "双击" },
                    onLongPress = { message = "长按" }
                )
            }
    ) {
        Text(text = message, modifier = Modifier.align(Alignment.Center))
    }
}


@Preview
@Composable
fun PointerInputDemo2() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(100.dp)
                .background(Color.Gray)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume() // 表示事件已处理
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
        {
            Text(text = "拖拽这里", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun PointerInputDemo3() {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotate ->
                    offsetX += pan.x
                    offsetY += pan.y
                    scale *= zoom
                    rotation += rotate
                }
            }
    ) {
        Box(
            modifier = Modifier
                .offset { androidx.compose.ui.unit.IntOffset(offsetX.toInt(), offsetY.toInt()) }
                .size(200.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation
                )
                .background(Color.Green)
        ){
            Text(text = "可拖拽、缩放和旋转", modifier = Modifier.align(Alignment.Center))
        }
    }
}