package edu.hrbeu.androidui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun ModifierAppearanceDemo1() {
    Box(
        modifier = Modifier
            .size(100.dp, 30.dp)                    // 尺寸
            .clip(RoundedCornerShape(20.dp))        // 圆角
            .background(Color.Gray)                 // 背景色
    )
}

@Preview(showBackground = true)
@Composable
fun ModifierAppearanceDemo2() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .border(2.dp, Color.Black)
    )
}

