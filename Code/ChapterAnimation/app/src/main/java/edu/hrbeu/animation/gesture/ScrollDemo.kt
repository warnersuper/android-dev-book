package edu.hrbeu.animation.gesture

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@Preview
@Composable
fun ScrollDemo1() {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        // 子项可以是多个 Box、Image、Text 等
        repeat(10) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(4.dp)
                    .background(Color.Gray)

            )
        }
    }

}
@Preview
@Composable
fun ScrollDemo2() {
    var offsetY by remember { mutableStateOf(0f) }

    val scrollState = rememberScrollableState { delta ->
        offsetY += delta
        delta // 返回实际消费的滚动距离
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .background(Color.Blue)
                .fillMaxSize(0.3f)
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(500, offsetY.roundToInt()/2) }
                .background(Color.Blue)
                .fillMaxSize(0.3f)
        )
    }
}