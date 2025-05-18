package edu.hrbeu.androidui.layoutUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun RowDemo1() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        ) {
            Text("按钮一")
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Text("按钮二")
        }

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
        ) {
            Text("按钮三")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowDemo2() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("左边")
        Text("中间")
        Text("右边")
    }
}

