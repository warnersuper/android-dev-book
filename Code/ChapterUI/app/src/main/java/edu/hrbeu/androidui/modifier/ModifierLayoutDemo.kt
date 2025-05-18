package edu.hrbeu.androidui.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.R
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

@Preview(showBackground = true)
@Composable
fun ModifierLayoutDemo1() {
    Box(
        modifier = Modifier
            .size(100.dp)

    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = Color.Red)
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(50.dp, 50.dp)
                .background(color = Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierLayoutDemo2() {
    Box(
        modifier = Modifier
            .size(100.dp)

    ) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(color = Color.White)
            )
        }
    }
}

