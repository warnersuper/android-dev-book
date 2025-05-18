package edu.hrbeu.androidui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//@Preview(showBackground = true)
@Composable
fun AnimateAsStateDemo1() {
    var isBlue by remember { mutableStateOf(true) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Gray,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor)
            .clickable { isBlue = !isBlue }
    )
}

//@Preview(showBackground = true)
@Composable
fun AnimateAsStateDemo2() {
    var isLarge by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (isLarge) 200.dp else 100.dp)

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Green)
            .clickable { isLarge = !isLarge }
    )
}

@Preview(showBackground = true)
@Composable
fun AnimateAsStateDemo3() {
    var expanded by remember { mutableStateOf(true) }

    val size by animateDpAsState(targetValue = if (expanded) 150.dp else 80.dp)
    val color by animateColorAsState(targetValue = if (expanded) Color.Green else Color.Gray)
    val cornerRadius by animateDpAsState(targetValue = if (expanded) 30.dp else 0.dp)

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color)
            .clickable { expanded = !expanded }
    )
}

