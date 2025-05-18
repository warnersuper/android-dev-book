package edu.hrbeu.chapterdatastore.demo5

import android.content.ContentResolver
import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment

class ContentResolverActivity : ComponentActivity() {

	private lateinit var resolver: ContentResolver

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// 初始化 ContentResolver
		resolver = contentResolver

		// 设置 Jetpack Compose UI
		setContent {
			MainScreen(resolver)
		}
	}
}

@Composable
fun MainScreen(resolver: ContentResolver) {
	var name by remember { mutableStateOf("") }
	var age by remember { mutableStateOf("") }
	var height by remember { mutableStateOf("") }
	var idEntry by remember { mutableStateOf("") }
	var displayText by remember { mutableStateOf("") }
	var label by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,  // 垂直居中
		horizontalAlignment = Alignment.CenterHorizontally  // 水平居中
	) {
		// 输入框
		TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
		TextField(value = age, onValueChange = { age = it }, label = { Text("Age") })
		TextField(value = height, onValueChange = { height = it }, label = { Text("Height") })
		TextField(value = idEntry, onValueChange = { idEntry = it }, label = { Text("ID Entry") })

		// 操作按钮
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			Button(onClick = { addRecord(resolver, name, age, height) { label = it } }) {
				Text("添加数据")
			}
			Button(onClick = {
				queryAllRecords(resolver) { newLabel, newText ->
					label = newLabel
					displayText = newText
				}
			}) {
				Text("查询全部")
			}
			Button(onClick = {
				// 清空显示内容
				clearDisplay { displayText = "" }
				// 删除数据库中的所有记录
				deleteAllRecords(resolver) { newLabel, newText ->
					label = newLabel
					displayText = newText
				}
			}) {
				Text("删除全部")
			}
		}

		// 显示区域
		Text(text = label, style = MaterialTheme.typography.bodyMedium)
		Text(text = displayText, style = MaterialTheme.typography.bodySmall)
	}
}

// 删除所有记录
fun deleteAllRecords(
	resolver: ContentResolver,
	onResult: (String, String) -> Unit
) {
	val rowsDeleted = resolver.delete(PeopleContract.CONTENT_URI, null, null)
	onResult("已删除记录数: $rowsDeleted", "")
}

// 添加记录
fun addRecord(
	resolver: ContentResolver,
	name: String,
	age: String,
	height: String,
	onResult: (String) -> Unit
) {
	val values = ContentValues().apply {
		put(PeopleContract.KEY_NAME, name)
		put(PeopleContract.KEY_AGE, age.toIntOrNull() ?: 0)
		put(PeopleContract.KEY_HEIGHT, height.toFloatOrNull() ?: 0f)
	}
	val newUri = resolver.insert(PeopleContract.CONTENT_URI, values)
	onResult("添加成功，URI: $newUri")
}

// 查询所有记录
fun queryAllRecords(
	resolver: ContentResolver,
	onResult: (String, String) -> Unit
) {
	val cursor = resolver.query(
		PeopleContract.CONTENT_URI,
		arrayOf(PeopleContract.KEY_ID, PeopleContract.KEY_NAME, PeopleContract.KEY_AGE, PeopleContract.KEY_HEIGHT),
		null, null, null
	)
	if (cursor == null) {
		onResult("数据库为空", "")
		return
	}

	// 获取列索引
	val idIndex = cursor.getColumnIndex(PeopleContract.KEY_ID)
	val nameIndex = cursor.getColumnIndex(PeopleContract.KEY_NAME)
	val ageIndex = cursor.getColumnIndex(PeopleContract.KEY_AGE)
	val heightIndex = cursor.getColumnIndex(PeopleContract.KEY_HEIGHT)

	// 检查列索引是否有效
	if (idIndex == -1 || nameIndex == -1 || ageIndex == -1 || heightIndex == -1) {
		onResult("查询列不存在", "")
		cursor.close()
		return
	}

	val msg = buildString {
		while (cursor.moveToNext()) {
			append("ID: ${cursor.getInt(idIndex)}, ")
			append("姓名: ${cursor.getString(nameIndex)}, ")
			append("年龄: ${cursor.getInt(ageIndex)}, ")
			append("身高: ${cursor.getFloat(heightIndex)}\n")
		}
	}
	onResult("数据库记录数: ${cursor.count}", msg)
	cursor.close()
}

// 清空显示内容
fun clearDisplay(onResult: () -> Unit) {
	onResult()
}