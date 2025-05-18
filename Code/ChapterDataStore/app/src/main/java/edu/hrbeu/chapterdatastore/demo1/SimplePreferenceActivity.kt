package edu.hrbeu.chapterdatastore.demo1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import edu.hrbeu.chapterdatastore.demo1.SimplePreferenceActivity.Companion.MODE
import edu.hrbeu.chapterdatastore.demo1.SimplePreferenceActivity.Companion.PREFERENCE_NAME

class SimplePreferenceActivity : ComponentActivity() {

	companion object {
		const val PREFERENCE_NAME = "SaveSetting"
		const val MODE = MODE_PRIVATE
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SimplePreferenceScreen()
		}
	}



}

@Preview
@Composable
fun SimplePreferenceScreen() {
	val context = LocalContext.current
	var name by remember { mutableStateOf("") }
	var age by remember { mutableStateOf("") }
	var height by remember { mutableStateOf("") }

	// 在启动时加载 SharedPreferences
	LaunchedEffect(Unit) {
		loadSharedPreferences(context).also {
			name = it.name
			age = it.age.toString()
			height = it.height.toString()
		}
		Toast.makeText(context, "数据读取成功", Toast.LENGTH_SHORT).show()
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		TextField(
			value = name,
			onValueChange = { name = it },
			label = { Text("Name") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(8.dp))
		TextField(
			value = age,
			onValueChange = { age = it },
			label = { Text("Age") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(8.dp))
		TextField(
			value = height,
			onValueChange = { height = it },
			label = { Text("Height") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(16.dp))
		Button(
			onClick = {
				saveSharedPreferences(context, name, age.toInt(), height.toFloat())
				Toast.makeText(context, "数据保存成功", Toast.LENGTH_SHORT).show()
			}
		) {
			Text("Save")
		}
	}
}

private fun loadSharedPreferences(context: Context): SharedPreferencesData {
	val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE)
	return SharedPreferencesData(
		name = sharedPreferences.getString("Name", "Tom") ?: "Tom",
		age = sharedPreferences.getInt("Age", 20) ?: 20 ,
		height = sharedPreferences.getFloat("Height", 1.81f) ?: 1.81f
	)
}

private fun saveSharedPreferences(context: Context, name: String, age: Int, height: Float) {
	val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE)
	with(sharedPreferences.edit()) {
		putString("Name", name)
		putInt("Age", age)
		putFloat("Height", height)
		apply()
	}
}

data class SharedPreferencesData(
	val name: String,
	val age: Int,
	val height: Float
)