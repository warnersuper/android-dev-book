package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun TabRowDemo1() {
    val tabs = listOf("首页", "推荐", "我的")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> Text("首页内容")
            1 -> Text("推荐内容")
            2 -> Text("我的内容")
        }
    }
}
