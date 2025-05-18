package edu.hrbeu.androidui.layoutUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun BoxDemo1() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        // 背景圆形
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Cyan, shape = CircleShape)
        )

        // 重叠的方形
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue.copy(alpha = 0.6f))
        )

        // 嵌套的小 Box，位于右下角
        Box(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .background(Color.Red)
        )
    }
}

