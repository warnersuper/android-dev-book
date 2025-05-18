package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Preview(showBackground = true)
@Composable
fun DialogDemo1() {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false  }) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("自定义弹窗", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("这是通过 Dialog 构建的自定义界面，你可以放任何内容。")

                    val onDismiss = null
                    Button(onClick = { showDialog = false  }) {
                        Text("关闭")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlertDialogDemo2() {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {  showDialog = false },
            title = { Text("提示") },
            text = { Text("您确定要执行这个操作吗？") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("取消")
                }
            }
        )
    }

}

