package edu.hrbeu.chapterdemo.presentation.setting

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.hrbeu.chapterdemo.data.base.AppData
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import androidx.compose.runtime.getValue


@Composable
fun SettingScreen(innerPadding: PaddingValues, dbAdapter: DBAdapter) {
    val viewModel: SettingViewModel = viewModel()     // 自动获取ViewModel
    val context = LocalContext.current

    val dbTableCount by viewModel.dbTableCount.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.observeAutoSaveAndDuplicates(context)
        viewModel.loadDBTableCount(dbAdapter)
    }

    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy ( 12.dp )
    ) {
        Text(
            text = "数据网址：${AppData.website}",
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "自动存储：",
            )
            Checkbox(
                checked = viewModel.autoSaveChecked,
                onCheckedChange = {
                    viewModel.autoSaveChecked = it
                }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "去除重复：",
            )
            Checkbox(
                checked = viewModel.removeDuplicatesChecked,
                onCheckedChange = {
                    viewModel.removeDuplicatesChecked = it
                }
            )
        }

        //HorizontalDivider()
        Text(
            text = "数据总量：${dbTableCount}",
        )

        Button(onClick = {
            dbAdapter.deleteAllData()
            viewModel.loadDBTableCount(dbAdapter)
            Toast.makeText(context, "数据库已经清空", Toast.LENGTH_SHORT).show()
        }) {
            Text("清空数据库")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSettingScreen(){
    //SettingScreen(innerPadding = PaddingValues(all = 16.dp))
}
