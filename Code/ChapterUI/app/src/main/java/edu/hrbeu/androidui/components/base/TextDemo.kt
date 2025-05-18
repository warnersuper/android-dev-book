package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme
@Preview(showBackground = true)
@Composable
fun TextDemo1() {
    Text(
        text = "Hello, Compose!",
        style = TextStyle(
            fontSize = 24.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TextDemo2() {
    val text = buildAnnotatedString {
        append("Hello, ")
        pushStyle(SpanStyle(fontWeight = FontWeight.W300, fontSize = 12.sp))
        append("Compose")
        pop()
        append(" World!")
    }

    Text(text = text, fontSize = 18.sp)
}


