package edu.hrbeu.animation.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


//@Preview
@Composable
fun DraggableDemo1() {
    var offsetX by remember { mutableStateOf(0f) }

    val draggableState = rememberDraggableState { delta ->
        offsetX += delta
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.toInt(), 0) }
            .size(120.dp)
            .background(Color.Blue)
            .draggable(
                orientation = Orientation.Horizontal,
                state = draggableState
            ),
        contentAlignment = Alignment.Center
    ) {
        Text("Drag me", color = Color.White)
    }
}


@Preview
@Composable
fun DraggableDemo2() {
    val scope = rememberCoroutineScope()
    val animOffset = remember { Animatable(0f) }

    val draggableState = rememberDraggableState { delta ->
        scope.launch {
            // 拖动过程中更新偏移
            animOffset.snapTo(animOffset.value + delta)
        }
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(animOffset.value.toInt(), 0) }
            .size(120.dp)
            .background(Color(0xFF4CAF50))
            .draggable(
                orientation = Orientation.Horizontal,
                state = draggableState,
                onDragStopped = { velocity ->
                    // 惯性滑动动画
                    scope.launch {
                        animOffset.animateDecay(
                            initialVelocity = velocity,
                            animationSpec = exponentialDecay()
                        )
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text("Fling me!", color = Color.White)
    }
}