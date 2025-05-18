package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme
import edu.hrbeu.androidui.R
@Preview(showBackground = true)
@Composable
fun IconDemo1() {
    Icon(
        imageVector = Icons.Filled.Email,
        contentDescription = "Email Icon",
        tint = Color.Blue,
        modifier = Modifier.size(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun IconDemo2() {
    Icon(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "App Icon",
        tint = Color.Unspecified, // 保持图标原始颜色
        modifier = Modifier.size(40.dp)
    )
}

