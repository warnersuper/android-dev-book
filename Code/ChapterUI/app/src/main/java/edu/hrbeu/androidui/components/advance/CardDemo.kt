package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun CardDemo1() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = { /* 可点击 */ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Card 标题", fontWeight = FontWeight.Bold)
            Text("这是 Card 容器中的内容。")
        }
    }
}


