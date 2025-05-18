package edu.hrbeu.chapterdatastore.demo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.xmlpull.v1.XmlPullParser
import java.io.IOException
import java.io.InputStream
import edu.hrbeu.chapterdatastore.R


class ResourceFileActivity : ComponentActivity() {

	private var displayText by mutableStateOf("")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ResourceFileDemoUI(
				onReadRawClick = ::readRawFile,
				onReadXmlClick = ::readXmlFile,
				onClearClick = { displayText = "" },
				displayText = displayText
			)
		}
	}

	private fun readRawFile() {
		try {
			val text = resources.openRawResource(R.raw.raw_file)
				.bufferedReader(Charsets.UTF_8)
				.use { it.readText() }
			displayText = text
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	private fun readXmlFile() {
		val parser: XmlPullParser = resources.getXml(R.xml.people)
		val result = StringBuilder()

		try {
			var eventType = parser.eventType

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG && parser.name == "person") {
					val name = parser.getAttributeValue(null, "name") ?: "Unknown"
					val age = parser.getAttributeValue(null, "age") ?: "?"
					val height = parser.getAttributeValue(null, "height") ?: "?"

					result.append("name: $name, age: $age, height: $height\n")
				}
				eventType = parser.next()
			}

		} catch (e: Exception) {
			e.printStackTrace()
			result.append("读取失败: ${e.message}")
		}

		displayText = result.toString()
	}


//	private fun readXmlFile() {
//		val parser: XmlPullParser = resources.getXml(R.xml.people)
//		var msg = ""
//		try {
//			while (parser.next() != XmlPullParser.END_DOCUMENT) {
//				val people = parser.name
//				var name: String? = null
//				var age: String? = null
//				var height: String? = null
//				if (people != null && people == "person") {
//					val count = parser.attributeCount
//					for (i in 0 until count) {
//						val attrName = parser.getAttributeName(i)
//						val attrValue = parser.getAttributeValue(i)
//						when (attrName) {
//							"name" -> name = attrValue
//							"age" -> age = attrValue
//							"height"  -> height = attrValue
//						}
//					}
//					if (name != null && age != null && height != null) {
//						msg += "name:$name,age:$age,height:$height\n"
//					}
//				}
//			}
//		} catch (e: Exception) {
//			e.printStackTrace()
//		}
//		displayText = msg
//	}
}



@Composable
fun ResourceFileDemoUI(
	onReadRawClick: () -> Unit,
	onReadXmlClick: () -> Unit,
	onClearClick: () -> Unit,
	displayText: String
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {



		Button(onClick = onReadRawClick) {
			Text("读取原始文件")
		}

		Spacer(modifier = Modifier.height(8.dp))

		Button(onClick = onReadXmlClick) {
			Text("读取XML文件")
		}

		Spacer(modifier = Modifier.height(8.dp))

		Button(onClick = onClearClick) {
			Text("清除显示内容")
		}
		Spacer(modifier = Modifier.height(16.dp))
		// 将 Text 组件水平居中对齐
		Text(
			text = displayText.ifEmpty { "No data loaded" },
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier
				.fillMaxWidth() // 使 Text 组件充满宽度
				.padding(8.dp)  // 添加内边距
		)
	}
}