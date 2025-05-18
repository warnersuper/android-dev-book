package edu.hrbeu.chapterdemo.presentation.history

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import edu.hrbeu.chapterdemo.presentation.setting.SettingViewModel


@Composable
fun HistoryScreen(
    innerPadding: PaddingValues,
    dbAdapter: DBAdapter,
    navigateToDetails: (String) -> Unit
) {
    val viewModel: HistoryViewModel = viewModel()     // 自动获取ViewModel
    val context = LocalContext.current
    val data by viewModel.data.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData(dbAdapter)
    }


    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data.size) { index ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F8E9) // 浅灰蓝背景
                ),
                onClick = {
                    println("Click index:$index")
                    navigateToDetails(data[index].link)
                }
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "[${data[index].author ?: ""}]" ,
                        )
                        Text(
                            text = data[index].pubDate,
                        )
                    }

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = data[index].title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()

                    )
                    Spacer(Modifier.height(5.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "ID:${data[index].id}"
                        )
                        Text(
                            text = "[HASH:${data[index].hashCode}]",
                        )


                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen(){
    //HistoryScreen(innerPadding = PaddingValues(all = 16.dp))
}

