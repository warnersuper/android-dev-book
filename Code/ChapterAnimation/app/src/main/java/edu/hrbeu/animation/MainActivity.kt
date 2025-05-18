package edu.hrbeu.animation

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hrbeu.androidui.animation.AnimateAsStateDemo1
import edu.hrbeu.androidui.animation.AnimateAsStateDemo2
import edu.hrbeu.androidui.animation.AnimateAsStateDemo3
import edu.hrbeu.androidui.animation.AnimatedContentDemo1
import edu.hrbeu.androidui.animation.AnimatedVisibilityDemo1
import edu.hrbeu.androidui.animation.InfiniteTransitionDemo1
import edu.hrbeu.androidui.animation.ModifierAnimationDemo1
import edu.hrbeu.androidui.animation.ModifierAnimationDemo2
import edu.hrbeu.androidui.animation.UpdateTransitionDemo1
import edu.hrbeu.animation.baseapi.AnimatableDemo1
import edu.hrbeu.animation.baseapi.AnimatableDemo2
import edu.hrbeu.animation.gesture.ClickableDemo1
import edu.hrbeu.animation.gesture.DraggableDemo1
import edu.hrbeu.animation.gesture.DraggableDemo2
import edu.hrbeu.animation.gesture.PointerInputDemo1
import edu.hrbeu.animation.gesture.PointerInputDemo2
import edu.hrbeu.animation.gesture.PointerInputDemo3
import edu.hrbeu.animation.gesture.ScrollDemo1
import edu.hrbeu.animation.gesture.ScrollDemo2
import edu.hrbeu.animation.gesture.TransformableDemo1
import edu.hrbeu.animation.specification.InfiniteRepeatableDemo1
import edu.hrbeu.animation.specification.KeyframesDemo1
import edu.hrbeu.animation.specification.RepeatableDemo1
import edu.hrbeu.animation.specification.SnapDemo1
import edu.hrbeu.animation.specification.SpringDemo1
import edu.hrbeu.animation.specification.TweenDemo1
import edu.hrbeu.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationTheme {
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
                title = { Text( if (isHome) "动画与手势示例" else target) },
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
                    .verticalScroll(scrollState) ){
                Text(
                    text = "基础动画API",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                var titles = listOf("animate*aState","updateTransition","InfiniteTransition","Animatable")
                var refs = listOf("AnimateAsStateDemo", "UpdateTransitionDemo","InfiniteTransitionDemo","AnimatableDemo")
                var demos = listOf(
                    listOf("颜色渐变","尺寸变化动画","组合多个属性动画"),
                    listOf("卡片展开动画"),
                    listOf("呼吸效果的圆形"),
                    listOf("Box平滑移动","方块落下"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]
                    val ref = refs[index]
                    for((indexExample, subTitle) in examples.withIndex()){
                        Button(onClick = {
                            target = "${ref}${indexExample + 1}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "动画规格",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("spring","tween","keyframes","repeatable","infiniteRepeatable","snap")
                refs = listOf("SpringDemo", "TweenDemo","KeyframesDemo","RepeatableDemo","InfiniteRepeatableDemo","SnapDemo")
                demos = listOf(
                    listOf("改变大小，带弹性动画"),
                    listOf("钮改变大小，匀速变化"),
                    listOf("透明度逐段变化"),
                    listOf("方块从左向右移动"),
                    listOf("透明度呼吸灯"),
                    listOf("点击切换颜色"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]
                    val ref = refs[index]
                    for((indexExample, subTitle) in examples.withIndex()){
                        Button(onClick = {
                            target = "${ref}${indexExample + 1}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "高级动画API",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("AnimatedVisibility","AnimatedContent","Modifier.animate*")
                refs = listOf("AnimatedVisibilityDemo", "AnimatedContentDemo","ModifierAnimationDemo")
                demos = listOf(
                    listOf("可切换可见性的文本块"),
                    listOf("数字递增时的切换动画"),
                    listOf("文本长度变化时自动动画过渡", "显示和隐藏"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]
                    val ref = refs[index]
                    for((indexExample, subTitle) in examples.withIndex()){
                        Button(onClick = {
                            target = "${ref}${indexExample + 1}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "手势系统",
                    modifier = Modifier.padding(10.dp),
                    style = chapterTitleStyle
                )
                titles = listOf("点击","拖动","多点触控","滚动","指针输入")
                refs = listOf("ClickableDemo", "DraggableDemo","TransformableDemo","ScrollDemo","PointerInputDemo")
                demos = listOf(
                    listOf("点击Box显示提示信息"),
                    listOf("水平拖动Box组件", "可拖拽的Box，支持松手后滑动"),
                    listOf("缩放、旋转和平移"),
                    listOf("使用horizontalScroll实现水平滚动","使用scrollable实现垂直手势滚动"),
                    listOf("点击、双击、长按检测", "拖动组件", "多指缩放与旋转"),
                )
                for((index, title) in titles.withIndex()){
                    Text("${title}示例")
                    val examples =  demos[index]
                    val ref = refs[index]
                    for((indexExample, subTitle) in examples.withIndex()){
                        Button(onClick = {
                            target = "${ref}${indexExample + 1}"
                            isHome = false
                        }) {
                            Text("示例${indexExample + 1}：$subTitle")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }
            }
        }else{
            Box(modifier = Modifier.padding(innerPadding)){
                key(refreshTrigger){
                    when(target){
                        "AnimateAsStateDemo1" -> AnimateAsStateDemo1()
                        "AnimateAsStateDemo2" -> AnimateAsStateDemo2()
                        "AnimateAsStateDemo3" -> AnimateAsStateDemo3()
                        "UpdateTransitionDemo1" -> UpdateTransitionDemo1()
                        "InfiniteTransitionDemo1" -> InfiniteTransitionDemo1()
                        "AnimatableDemo1" -> AnimatableDemo1()
                        "AnimatableDemo2" -> AnimatableDemo2()

                        "SpringDemo1" -> SpringDemo1()
                        "TweenDemo1" -> TweenDemo1()
                        "KeyframesDemo1" -> KeyframesDemo1()
                        "RepeatableDemo1" -> RepeatableDemo1()
                        "InfiniteRepeatableDemo1" -> InfiniteRepeatableDemo1()
                        "SnapDemo1" -> SnapDemo1()

                        "AnimatedVisibilityDemo1" -> AnimatedVisibilityDemo1()
                        "AnimatedContentDemo1" -> AnimatedContentDemo1()
                        "ModifierAnimationDemo1" -> ModifierAnimationDemo1()
                        "ModifierAnimationDemo2" -> ModifierAnimationDemo2()

                        "ClickableDemo1" -> ClickableDemo1()
                        "DraggableDemo1" -> DraggableDemo1()
                        "DraggableDemo2" -> DraggableDemo2()
                        "TransformableDemo1" -> TransformableDemo1()
                        "ScrollDemo1" -> ScrollDemo1()
                        "ScrollDemo2" -> ScrollDemo2()
                        "PointerInputDemo1" -> PointerInputDemo1()
                        "PointerInputDemo2" -> PointerInputDemo2()
                        "PointerInputDemo3" -> PointerInputDemo3()

                        else -> println("ERROR")
                    }
                }

            }

        }

    }

}

