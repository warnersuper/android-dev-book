package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun IconButtonDemo1() {
    IconButton(onClick = { /* 执行操作 */ }) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Like",
            tint = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonDemo2() {
    var isFavorite by remember { mutableStateOf(false) }

    IconButton(onClick = { isFavorite = !isFavorite }) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isFavorite) "取消收藏" else "添加收藏",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}

