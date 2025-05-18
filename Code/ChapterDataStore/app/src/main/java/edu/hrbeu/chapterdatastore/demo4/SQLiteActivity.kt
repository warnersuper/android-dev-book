package edu.hrbeu.chapterdatastore.demo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent

class SQLiteActivity : ComponentActivity() {

	private lateinit var dbAdapter: DBAdapter
	private var label by mutableStateOf("")
	private var display by mutableStateOf("")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		dbAdapter = DBAdapter(this)
		dbAdapter.open()
		setContent {
			SQLiteDemoUI(
					label = label,
					display = display,
					onAdd = { name, age, height ->
							val people = People().apply {
							this.Name = name
							this.Age = age.toInt()
							this.Height = height.toFloat()
					}
							val column = dbAdapter.insert(people)
							label = if (column == -1L) "添加数据失败" else "成功添加数据，ID：$column"
                },
			onQueryAll = {
					val peoples = dbAdapter.queryAllData()
			if (peoples.isNullOrEmpty()) {
				label = "数据库中没有数据"
				display = ""
			} else {
				label = "数据库："
				display = peoples.joinToString("\n") { it.toString() }
			}
                },
			onDeleteAll = {
					dbAdapter.deleteAllData()
					label = "已删除全部数据"
					display = ""
			}
            )
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		dbAdapter.close()
	}
}

@Composable
fun SQLiteDemoUI(
	label: String,
	display: String,
	onAdd: (String, String, String) -> Unit,
	onQueryAll: () -> Unit,
	onDeleteAll: () -> Unit
) {
	var name by remember { mutableStateOf("") }
	var age by remember { mutableStateOf("") }
	var height by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = label, style = MaterialTheme.typography.titleMedium)
		Spacer(modifier = Modifier.height(8.dp))

		TextField(
			value = name,
			onValueChange = { name = it },
			label = { Text("姓名") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(8.dp))

		TextField(
			value = age,
			onValueChange = { age = it },
			label = { Text("年龄") },
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(8.dp))

		TextField(
			value = height,
			onValueChange = { height = it },
			label = { Text("身高") },
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(16.dp))

		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier.fillMaxWidth()
		) {
			Button(onClick = { onAdd(name, age, height) }) {
				Text("添加")
			}
			Button(onClick = onQueryAll) {
				Text("查询全部")
			}
			Button(onClick = onDeleteAll) {
				Text("删除全部")
			}
		}
		Spacer(modifier = Modifier.height(16.dp))

		Text(text = display, style = MaterialTheme.typography.bodyMedium)
	}
}