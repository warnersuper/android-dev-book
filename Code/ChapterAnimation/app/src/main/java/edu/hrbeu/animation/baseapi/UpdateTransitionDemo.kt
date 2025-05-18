package edu.hrbeu.androidui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
fun UpdateTransitionDemo1() {
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "cardTransition")

    val cardColor by transition.animateColor(label = "color") { isExpanded ->
        if (isExpanded) Color(0xFFBBDEFB) else Color.White
    }

    val cardHeight by transition.animateDp(label = "height") { isExpanded ->
        if (isExpanded) 200.dp else 100.dp
    }

    val cornerRadius by transition.animateDp(label = "corner") { isExpanded ->
        if (isExpanded) 16.dp else 4.dp
    }

    Surface(
        color = cardColor,
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickable { expanded = !expanded }
            .padding(16.dp),
        shadowElevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(if (expanded) "点击收起" else "点击展开")
        }
    }
}
