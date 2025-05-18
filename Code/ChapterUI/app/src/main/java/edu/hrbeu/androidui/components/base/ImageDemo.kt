package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

import edu.hrbeu.androidui.R

@Preview(showBackground = true)
@Composable
fun ImageDemo1() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "示例图片",
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}


