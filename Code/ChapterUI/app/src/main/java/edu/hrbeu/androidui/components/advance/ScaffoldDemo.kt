package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme
import kotlinx.coroutines.launch


//@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTypicalStructure() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("标题（topBar区域）") })
        },
        bottomBar = {
            BottomAppBar {
                Text("底部内容，如工具栏或导航栏（bottomBar区域）")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*操作*/ }) {
                Icon(Icons.Default.Add, contentDescription = "添加")
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text("主要内容区域（content区域）")
            }
        }
    )

}




//@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo1() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("首页") },
                navigationIcon = {
                    IconButton(onClick = { /* Home */ }) {
                        Icon(Icons.Default.Home, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* 新增内容 */ }) {
                Icon(Icons.Default.Add, contentDescription = "添加")
            }
        }
    ) { paddingValues ->
        // 主体内容，注意加 padding
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("欢迎使用 Jetpack Compose Scaffold")
        }
    }
}




//@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun ScaffoldDemo2() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                onClick = {
                    // show snackbar as a suspend function
                    scope.launch {
                        snackbarHostState.showSnackbar("Snackbar # ${++clickCount}")
                    }
                }
            ) {
                Text("Show snackbar")
            }
        },
        content = { innerPadding ->
            Text(
                text = "Body content",
                modifier = Modifier.padding(innerPadding).fillMaxSize().wrapContentSize()
            )
        }
    )
}

//@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo3() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("首页", "音乐", "收藏")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("首页") },
                navigationIcon = {
                    IconButton(onClick = { /* Home */ }) {
                        Icon(Icons.Default.Home, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },

        content = { innerPadding ->
            Text(
                text = "Body content",
                modifier = Modifier.padding(innerPadding).fillMaxSize().wrapContentSize()
            )
        }
    )

}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo4() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("导航项 1", modifier = Modifier.padding(16.dp))
                Text("导航项 2", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { /* 信息 */ }) {
                        Icon(Icons.Default.Info, contentDescription = "信息")
                    }
                    IconButton(onClick = { /* 设置 */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "设置")
                    }
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text("带底部栏页面") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "菜单")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("页面内容区域")
            }
        }
    }
}


