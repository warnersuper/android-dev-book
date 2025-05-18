package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.R
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun ProgressIndicatorDemo1() {
    var progress by remember { mutableFloatStateOf(0.1f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(progress = { progress })
        Text("设置进度:")
        Slider(
            modifier = Modifier.width(300.dp),
            value = progress,
            valueRange = 0f..1f,
            onValueChange = { progress = it },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressIndicatorDemo2() {
    var progress by remember { mutableFloatStateOf(0.1f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(progress = { progress })
        Text("设置进度:")
        Slider(
            modifier = Modifier.width(300.dp),
            value = progress,
            valueRange = 0f..1f,
            onValueChange = { progress = it },
        )
    }
}


