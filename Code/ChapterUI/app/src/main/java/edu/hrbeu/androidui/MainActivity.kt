package edu.hrbeu.androidui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hrbeu.androidui.basicconcepts.CountLoggerExample
import edu.hrbeu.androidui.basicconcepts.CounterExample
import edu.hrbeu.androidui.basicconcepts.CounterRemember
import edu.hrbeu.androidui.basicconcepts.CounterSave
import edu.hrbeu.androidui.basicconcepts.CounterScreen1
import edu.hrbeu.androidui.basicconcepts.CounterScreen2
import edu.hrbeu.androidui.basicconcepts.DerivedStateExample
import edu.hrbeu.androidui.basicconcepts.DisposableScreen
import edu.hrbeu.androidui.basicconcepts.Greeting
import edu.hrbeu.androidui.basicconcepts.RememberCoroutineScopeExample
import edu.hrbeu.androidui.basicconcepts.SideEffectExample
import edu.hrbeu.androidui.basicconcepts.SnapshotFlowExample
import edu.hrbeu.androidui.basicconcepts.TimerLoggerComposable
import edu.hrbeu.androidui.basicconcepts.UserProfile
import edu.hrbeu.androidui.components.advance.AlertDialogDemo2
import edu.hrbeu.androidui.components.advance.CardDemo1
import edu.hrbeu.androidui.components.advance.DialogDemo1
import edu.hrbeu.androidui.components.advance.DropdownMenuDemo1
import edu.hrbeu.androidui.components.advance.LazyColumnDemo1
import edu.hrbeu.androidui.components.advance.LazyColumnDemo2
import edu.hrbeu.androidui.components.advance.LazyColumnDemo3
import edu.hrbeu.androidui.components.advance.LazyRowDemo1
import edu.hrbeu.androidui.components.advance.LazyRowDemo2
import edu.hrbeu.androidui.components.advance.ScaffoldDemo1
import edu.hrbeu.androidui.components.advance.ScaffoldDemo2
import edu.hrbeu.androidui.components.advance.ScaffoldDemo3
import edu.hrbeu.androidui.components.advance.ScaffoldDemo4
import edu.hrbeu.androidui.components.advance.ScaffoldTypicalStructure
import edu.hrbeu.androidui.components.advance.ScrollableTabRowDemo1
import edu.hrbeu.androidui.components.advance.SurfaceDemo1
import edu.hrbeu.androidui.components.advance.SurfaceDemo2
import edu.hrbeu.androidui.components.advance.TabRowDemo1
import edu.hrbeu.androidui.components.base.ButtonDemo1
import edu.hrbeu.androidui.components.base.ButtonDemo2
import edu.hrbeu.androidui.components.base.CheckBoxDemo1
import edu.hrbeu.androidui.components.base.IconButtonDemo1
import edu.hrbeu.androidui.components.base.IconButtonDemo2
import edu.hrbeu.androidui.components.base.IconDemo1
import edu.hrbeu.androidui.components.base.IconDemo2
import edu.hrbeu.androidui.components.base.ImageDemo1
import edu.hrbeu.androidui.components.base.ProgressIndicatorDemo1
import edu.hrbeu.androidui.components.base.ProgressIndicatorDemo2
import edu.hrbeu.androidui.components.base.RadioButtonDemo1
import edu.hrbeu.androidui.components.base.SliderDemo1
import edu.hrbeu.androidui.components.base.SwitchDemo1
import edu.hrbeu.androidui.components.base.SwitchDemo2
import edu.hrbeu.androidui.components.base.TextDemo1
import edu.hrbeu.androidui.components.base.TextDemo2
import edu.hrbeu.androidui.components.base.TextFieldDemo1
import edu.hrbeu.androidui.components.base.TextFieldDemo2
import edu.hrbeu.androidui.layoutUI.BoxDemo1
import edu.hrbeu.androidui.layoutUI.ColumnDemo1
import edu.hrbeu.androidui.layoutUI.RowDemo1
import edu.hrbeu.androidui.layoutUI.RowDemo2
import edu.hrbeu.androidui.layoutUI.SpacerDemo1
import edu.hrbeu.androidui.layoutUI.SpacerDemo2
import edu.hrbeu.androidui.modifier.ModifierAlignDemo1
import edu.hrbeu.androidui.modifier.ModifierAppearanceDemo1
import edu.hrbeu.androidui.modifier.ModifierAppearanceDemo2
import edu.hrbeu.androidui.modifier.ModifierInteractiveDemo1
import edu.hrbeu.androidui.modifier.ModifierInteractiveDemo2
import edu.hrbeu.androidui.modifier.ModifierLayoutDemo1
import edu.hrbeu.androidui.modifier.ModifierLayoutDemo2
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidUIDemoTheme {

                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    var isHome by remember { mutableStateOf(true) }
    var target by remember { mutableStateOf("") }
    var refreshTrigger  by remember { mutableStateOf(0) }

    val scrollState = rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text( if (isHome) "Android用户界面" else target) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,      // 背景色
                    titleContentColor = Color.Black   // 标题颜色
                ),
                navigationIcon = {
                    IconButton(onClick = { isHome = true }) {
                        Icon(if (isHome) Icons.Default.Star else Icons.Default.Home, contentDescription = null)
                    }
                },
                actions = {
                    if(!isHome) {
                        IconButton(
                            onClick = {refreshTrigger++}
                        ) {
                            Text("刷新")
                        }

                        IconButton(
                            onClick = { isHome = true }
                        ) {
                            Text("返回")
                        }

                    }
                }

            )

        },
    ) { innerPadding ->
        if(isHome){
            val chapterTitleStyle = TextStyle(
                fontSize = 24.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
            )

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState) )
            {
                Text(
                    text = "基本概念",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                var titles = listOf("Composable函数","重组机制","状态管理","状态提升","副作用函数")
                var refs = listOf(
                    listOf("Greeting"),
                    listOf("CounterExample"),
                    listOf("CounterRemember","CounterSave"),
                    listOf("CounterScreen1","CounterScreen2"),
                    listOf("UserProfile", "TimerLoggerComposable","DisposableScreen","SideEffectExample",
                        "RememberCoroutineScopeExample","DerivedStateExample","CountLoggerExample","SnapshotFlowExample")
                )
                var demos = listOf(
                    listOf("Greeting"),
                    listOf("Compose重组示例"),
                    listOf("remember 示例","rememberSaveable示例"),
                    listOf("状态提升前","状态提后"),
                    listOf("LaunchedEffect","定时器","DisposableEffect","SideEffect","rememberCoroutineScope","derivedStateOf"
                        ,"rememberUpdatedState","snapshotFlow ")


                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]

                    for((indexExample, subTitle) in examples.withIndex()){
                        val ref = refs[index][indexExample]
                        Button(onClick = {
                            target = "${ref}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                //========================================================
                Text(
                    text = "Modifier修饰符",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("布局相关","对齐方式","外观样式","交互相关")
                refs = listOf(
                    listOf("ModifierLayoutDemo1","ModifierLayoutDemo2"),
                    listOf("ModifierAlignDemo1"),
                    listOf("ModifierAppearanceDemo1","ModifierAppearanceDemo2"),
                    listOf("ModifierInteractiveDemo1","ModifierInteractiveDemo2"),
                )
                demos = listOf(
                    listOf("Modifier布局"," Modifier布局"),
                    listOf("Modifier对齐"),
                    listOf("Modifier外观","Modifier外观"),
                    listOf("Modifier交互","Modifier交互"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]

                    for((indexExample, subTitle) in examples.withIndex()){
                        val ref = refs[index][indexExample]
                        Button(onClick = {
                            target = "${ref}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                //========================================================
                Text(
                    text = "基础UI组件",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("Text","Icon","Image","TextField","Button","IconButton","CheckBox",
                    "RadioButton","Switch","Slider","ProgressIndicator")
                refs = listOf(
                    listOf("TextDemo1","TextDemo2"),
                    listOf("IconDemo1","IconDemo2"),
                    listOf("ImageDemo1"),
                    listOf("TextFieldDemo1","TextFieldDemo2"),
                    listOf("ButtonDemo1","ButtonDemo2"),
                    listOf("IconButtonDemo1","IconButtonDemo2"),
                    listOf("CheckBoxDemo1"),
                    listOf("RadioButtonDemo1"),
                    listOf("SwitchDemo1","SwitchDemo2"),
                    listOf("SliderDemo1"),
                    listOf("ProgressIndicatorDemo1","ProgressIndicatorDemo2"),
                )
                demos = listOf(
                    listOf("TextDemo1","TextDemo2"),
                    listOf("IconDemo1","IconDemo2"),
                    listOf("ImageDemo1"),
                    listOf("TextFieldDemo1","TextFieldDemo2"),
                    listOf("ButtonDemo1","ButtonDemo2"),
                    listOf("IconButtonDemo1","IconButtonDemo2"),
                    listOf("CheckBoxDemo1"),
                    listOf("RadioButtonDemo1"),
                    listOf("SwitchDemo1","SwitchDemo2"),
                    listOf("SliderDemo1"),
                    listOf("ProgressIndicatorDemo1","ProgressIndicatorDemo2"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]

                    for((indexExample, subTitle) in examples.withIndex()){
                        val ref = refs[index][indexExample]
                        Button(onClick = {
                            target = "${ref}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                //========================================================
                Text(
                    text = "布局组件",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("Box","Row","Column","Spacer")
                refs = listOf(
                    listOf("BoxDemo1"),
                    listOf("RowDemo1","RowDemo2"),
                    listOf("ColumnDemo1"),
                    listOf("SpacerDemo1","SpacerDemo2"),
                )
                demos = listOf(
                    listOf("BoxDemo1"),
                    listOf("RowDemo1","RowDemo2"),
                    listOf("ColumnDemo1"),
                    listOf("SpacerDemo1","SpacerDemo2"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]

                    for((indexExample, subTitle) in examples.withIndex()){
                        val ref = refs[index][indexExample]
                        Button(onClick = {
                            target = "${ref}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                //========================================================
                Text(
                    text = "高级组件",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("Surface","Card","Dialog","DropdownMenu","Scaffold","LazyColumn","LazyRow","TabRow","ScrollableTabRow")
                refs = listOf(
                    listOf("SurfaceDemo1", "SurfaceDemo2"),
                    listOf("CardDemo1"),
                    listOf("DialogDemo1","AlertDialogDemo2"),
                    listOf("DropdownMenuDemo1"),
                    listOf("ScaffoldTypicalStructure", "ScaffoldDemo1", "ScaffoldDemo2", "ScaffoldDemo3", "ScaffoldDemo4"),
                    listOf("LazyColumnDemo1","LazyColumnDemo2","LazyColumnDemo3"),
                    listOf("LazyRowDemo1","LazyRowDemo2"),
                    listOf("TabRowDemo1"),
                    listOf("ScrollableTabRowDemo1")
                )
                demos = listOf(
                    listOf("带阴影的卡片容器", "对话框底板布局"),
                    listOf("快速构建内容卡片"),
                    listOf("Dialog","AlertDialog"),
                    listOf("DropdownMenu"),
                    listOf("Scaffold典型结构","带顶部栏和悬浮按钮", "带SnackbarHost", "带顶部栏和导航", "带BottomAppBar和Drawer"),
                    listOf("基础文字列表","复杂卡片列表","使用ListItem组件"),
                    listOf("分类标签","图片轮播示"),
                    listOf("水平标签栏"),
                    listOf("横向滚动标签栏"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]

                    for((indexExample, subTitle) in examples.withIndex()){
                        val ref = refs[index][indexExample]
                        Button(onClick = {
                            target = "${ref}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))


            }
        }else{
            Box(modifier = Modifier.padding(innerPadding)){
                key(refreshTrigger){
                    when(target){
                        "Greeting" -> Greeting("Android")
                        "CounterExample" -> CounterExample()
                        "CounterRemember" -> CounterRemember()
                        "CounterSave" -> CounterSave()
                        "CounterScreen1" -> CounterScreen1()
                        "CounterScreen2" -> CounterScreen2()
                        "UserProfile" -> UserProfile("100003")
                        "TimerLoggerComposable" -> TimerLoggerComposable()
                        "DisposableScreen" -> DisposableScreen()
                        "SideEffectExample" -> SideEffectExample()
                        "RememberCoroutineScopeExample" -> RememberCoroutineScopeExample()
                        "DerivedStateExample" -> DerivedStateExample()
                        "CountLoggerExample" -> CountLoggerExample()
                        "SnapshotFlowExample" -> SnapshotFlowExample()

                        "ModifierLayoutDemo1" -> ModifierLayoutDemo1()
                        "ModifierLayoutDemo2" -> ModifierLayoutDemo2()
                        "ModifierAlignDemo1" -> ModifierAlignDemo1()
                        "ModifierAppearanceDemo1" -> ModifierAppearanceDemo1()
                        "ModifierAppearanceDemo2" -> ModifierAppearanceDemo2()
                        "ModifierInteractiveDemo1" -> ModifierInteractiveDemo1()
                        "ModifierInteractiveDemo2" -> ModifierInteractiveDemo2()

                        "TextDemo1" -> TextDemo1()
                        "TextDemo2" -> TextDemo2()
                        "IconDemo1" -> IconDemo1()
                        "IconDemo2" -> IconDemo2()
                        "ImageDemo1" -> ImageDemo1()
                        "TextFieldDemo1" -> TextFieldDemo1()
                        "TextFieldDemo2" -> TextFieldDemo2()
                        "ButtonDemo1" -> ButtonDemo1()
                        "ButtonDemo2" -> ButtonDemo2()
                        "IconButtonDemo1" -> IconButtonDemo1()
                        "IconButtonDemo2" -> IconButtonDemo2()
                        "CheckBoxDemo1" -> CheckBoxDemo1()
                        "RadioButtonDemo1" -> RadioButtonDemo1()
                        "SwitchDemo1" -> SwitchDemo1()
                        "SwitchDemo2" -> SwitchDemo2()
                        "SliderDemo1" -> SliderDemo1()
                        "ProgressIndicatorDemo1" -> ProgressIndicatorDemo1()
                        "ProgressIndicatorDemo2" -> ProgressIndicatorDemo2()

                        "BoxDemo1" -> BoxDemo1()
                        "RowDemo1" -> RowDemo1()
                        "RowDemo2" -> RowDemo2()
                        "ColumnDemo1" -> ColumnDemo1()
                        "SpacerDemo1" -> SpacerDemo1()
                        "SpacerDemo2" -> SpacerDemo2()

                        "SurfaceDemo1" -> SurfaceDemo1()
                        "SurfaceDemo2" -> SurfaceDemo2()
                        "CardDemo1" -> CardDemo1()
                        "DialogDemo1" -> DialogDemo1()
                        "AlertDialogDemo2" -> AlertDialogDemo2()
                        "DropdownMenuDemo1" -> DropdownMenuDemo1()
                        "ScaffoldTypicalStructure" -> ScaffoldTypicalStructure()
                        "ScaffoldDemo1" -> ScaffoldDemo1()
                        "ScaffoldDemo2" -> ScaffoldDemo2()
                        "ScaffoldDemo3" -> ScaffoldDemo3()
                        "ScaffoldDemo4" -> ScaffoldDemo4()
                        "LazyColumnDemo1" -> LazyColumnDemo1()
                        "LazyColumnDemo2" -> LazyColumnDemo2()
                        "LazyColumnDemo3" -> LazyColumnDemo3()
                        "LazyRowDemo1" -> LazyRowDemo1()
                        "LazyRowDemo2" -> LazyRowDemo2()
                        "TabRowDemo1" -> TabRowDemo1()
                        "ScrollableTabRowDemo1" -> ScrollableTabRowDemo1()


                        else -> println("ERROR")
                    }
                }

            }

        }

    }

}

