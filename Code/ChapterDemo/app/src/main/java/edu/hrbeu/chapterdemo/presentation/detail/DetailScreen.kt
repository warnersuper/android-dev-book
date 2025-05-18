package edu.hrbeu.chapterdemo.presentation.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun DetailScreen(innerPadding: PaddingValues, url: String) {
    Box(
        modifier = Modifier.padding(innerPadding)
    ){
        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient() // 避免跳转外部浏览器
                settings.javaScriptEnabled = true // 有些页面可能需要
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen(){
    DetailScreen(innerPadding = PaddingValues(all = 16.dp), "https://www.baidu.com")
}
