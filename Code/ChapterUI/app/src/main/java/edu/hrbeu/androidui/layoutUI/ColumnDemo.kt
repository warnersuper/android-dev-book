package edu.hrbeu.androidui.layoutUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun ColumnDemo1() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("登录", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("用户名") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("密码") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = { /* 登录操作 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("登录")
        }
    }
}
