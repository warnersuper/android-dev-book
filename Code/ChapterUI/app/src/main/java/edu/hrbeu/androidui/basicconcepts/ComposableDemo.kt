package edu.hrbeu.androidui.basicconcepts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Greeting(name: String) {
        Text(text = "Hello, $name!")
}

@Preview
@Composable
fun DefaultPreview(){
        Greeting("Android")
}