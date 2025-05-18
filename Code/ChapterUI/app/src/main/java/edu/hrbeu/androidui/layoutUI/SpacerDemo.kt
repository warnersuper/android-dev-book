package edu.hrbeu.androidui.layoutUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun SpacerDemo1() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "上面的文字")

        Spacer(modifier = Modifier.height(20.dp)) // 插入 20dp 高的空白

        Text(text = "下面的文字")
    }
}

@Preview(showBackground = true)
@Composable
fun SpacerDemo2() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("左边")
        Spacer(modifier = Modifier.weight(1f)) // 弹性撑开空间
        Text("中间")
        Spacer(modifier = Modifier.weight(1f)) // 弹性撑开空间
        Text("右边")
    }
}
