package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//@Preview(showBackground = true)
@Composable
fun LazyColumnDemo1() {
    val itemList = listOf("苹果", "香蕉", "橘子", "西瓜", "芒果")
    LazyColumn {
        items(itemList.size) { index ->
            Text(
                text = itemList[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun LazyColumnDemo2() {
    val products = listOf("书包", "笔记本", "钢笔")
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(products[index])
                    Button(onClick = { /* 加入购物车 */ }) {
                        Text("添加")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnDemo3() {
    LazyColumn {
        items(3) { index ->
            ListItem(
                headlineContent = { Text("这是第${index + 1}行标题") },
                overlineContent = { Text("顶部补充内容") },
                supportingContent = { Text("副内容说明") },
                leadingContent = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                    )
                },
                trailingContent = { Text("尾部内容") }
            )
            HorizontalDivider()
        }
    }
}

