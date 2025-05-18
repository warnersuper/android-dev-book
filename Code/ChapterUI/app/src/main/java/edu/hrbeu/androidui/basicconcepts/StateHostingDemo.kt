package edu.hrbeu.androidui.basicconcepts

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Counter1() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text("Count: $count")
    }
}

@Composable
fun CounterScreen1() {
    Counter1()
}


//===================== 状态提升 ===========================

@Composable
fun Counter2(count: Int, onIncrement: () -> Unit) {
    Button(onClick = onIncrement) {
        Text("Count: $count")
    }
}

@Composable
fun CounterScreen2() {
    var count by remember { mutableStateOf(0) }
    Counter2(count = count, onIncrement = { count++ })
}