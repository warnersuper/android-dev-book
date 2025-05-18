package edu.hrbeu.androidui.components.advance

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun ScrollableTabRowDemo1() {
    val tabs = listOf("推荐", "热点", "娱乐", "科技", "财经", "体育", "更多")
    var selectedTabIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(title) }
            )
        }
    }
}
