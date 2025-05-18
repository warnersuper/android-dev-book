package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun ButtonDemo1() {
    Button(onClick = { println("按钮被点击了") }) {
        Text("点击我")
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonDemo2() {
    Button(
        onClick = { println("自定义按钮点击") },
        colors = ButtonDefaults.buttonColors(Color.Red),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Default.Favorite, contentDescription = "图标", tint = Color.White)
        Text("喜欢", color = Color.White)
    }
}

