package edu.hrbeu.animation.gesture

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


@Preview(showBackground = true)
@Composable
fun ClickableDemo1() {
    var clicked by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(if (clicked) Color.Green else Color.Gray)
            .clickable {
                clicked = !clicked
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (clicked) "Clicked!" else "Click Me",
            color = Color.White
        )
    }
}
