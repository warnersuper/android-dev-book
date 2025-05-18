package edu.hrbeu.androidui.basicconcepts

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class StateDemo {
}

@Preview
@Composable
fun CounterRemember() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text(text = "点击次数: $count")
    }
}

@Preview
@Composable
fun CounterSave() {
    var count by rememberSaveable { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text(text = "点击次数: $count")
    }
}

