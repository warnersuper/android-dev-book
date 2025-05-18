package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun SurfaceDemo1() {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("标题", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("这里是一些内容，展示在一个带圆角和阴影的卡片里。")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SurfaceDemo2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x88000000)), // 半透明遮罩背景
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("提示", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("确定要执行这个操作吗？")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(onClick = { /* 取消逻辑 */ }) {
                        Text("取消")
                    }
                    Button(onClick = { /* 确认逻辑 */ }) {
                        Text("确认")
                    }
                }
            }
        }
    }
}


