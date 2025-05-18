package edu.hrbeu.chapterdemo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.hrbeu.chapterdemo.ui.theme.ChapterDemoTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.hrbeu.chapterdemo.presentation.detail.DetailScreen
import edu.hrbeu.chapterdemo.presentation.history.HistoryScreen
import edu.hrbeu.chapterdemo.presentation.history.HistoryViewModel
import edu.hrbeu.chapterdemo.presentation.home.HomeScreen
import edu.hrbeu.chapterdemo.presentation.home.HomeViewModel
import edu.hrbeu.chapterdemo.Route
import edu.hrbeu.chapterdemo.presentation.setting.SettingScreen
import edu.hrbeu.chapterdemo.presentation.setting.SettingViewModel
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import edu.hrbeu.chapterdemo.data.db.DBNews



class MainActivity : ComponentActivity() {
    private lateinit var dbAdapter: DBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbAdapter = DBAdapter(this)
        dbAdapter.open()
        enableEdgeToEdge()
        setContent {
            ChapterDemoTheme {
                MainScreen(dbAdapter)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbAdapter.close()
    }
}



//@Preview(showBackground = true, widthDp = 450, heightDp = 600)
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(dbAdapter: DBAdapter) {

    var selectedItem by rememberSaveable { mutableStateOf(0)}
    var refreshCounter by rememberSaveable { mutableStateOf(0) }
    val navController = rememberNavController()
    val items = listOf("首页", "历史", "设置")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.DateRange, Icons.Filled.Settings)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.DateRange, Icons.Outlined.Settings)

    val backStackState = navController.currentBackStackEntryAsState().value

    //是否隐藏底部导航栏
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.HistoryScreen.route ||
                backStackState?.destination?.route == Route.SettingScreen.route
    }

    Scaffold(
        topBar = {
            if (isBottomBarVisible) {
                TopAppBar(
                    title = { Text(items[selectedItem]) },
                    actions = {
                        when (selectedItem) {
                            0 -> {
                                IconButton(
                                    onClick = {
                                        refreshCounter += 1
                                        println("Refresh:$refreshCounter")

                                    }
                                ) {
                                    Text("刷新")
                                }
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(selectedIcons[selectedItem], contentDescription = null)
                        }
                    }
                )
            }else{
                TopAppBar(
                    title = {
                        Text(
                            text = "返回",
                            modifier = Modifier.clickable{
                                navController.navigateUp()
                            }
                        )
                    },
                    actions = {

                    },
                    navigationIcon = {
                        IconButton(onClick = {  navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (isBottomBarVisible) {
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
                            onClick = {
                                selectedItem = index
                                when (selectedItem) {
                                    0 -> navController.navigate(Route.HomeScreen.route)
                                    1 -> navController.navigate(Route.HistoryScreen.route)
                                    2 -> navController.navigate(Route.SettingScreen.route)
                                }
                            }
                        )
                    }
                }
            }
        },

        content = { innerPadding ->
            NavHost(navController = navController, startDestination = "homeScreen"){
                composable(
                    route = Route.HomeScreen.route
                ){
                    val viewModel: HomeViewModel = viewModel()
                    HomeScreen(
                        innerPadding = innerPadding,
                        dbAdapter = dbAdapter,
                        viewModel = viewModel,
                        refreshCounter = refreshCounter,
                        navigateToDetails = { url ->
                            navController.currentBackStackEntry?.savedStateHandle?.set("url", url)
                            navController.navigate(Route.DetailsScreen.route)
                        })
                }

                composable(
                    route = Route.HistoryScreen.route
                ){
                    val viewModel: HistoryViewModel = viewModel()
                    HistoryScreen(
                        innerPadding,
                        dbAdapter,
                        navigateToDetails = { url ->
                            navController.currentBackStackEntry?.savedStateHandle?.set("url", url)
                            navController.navigate(Route.DetailsScreen.route)
                        }
                    )
                }

                composable(
                    route = Route.SettingScreen.route
                ){
                    val viewModel: SettingViewModel = viewModel()
                    SettingScreen(
                        innerPadding = innerPadding,
                        dbAdapter = dbAdapter,
                    )
                }

                composable(
                    route = Route.DetailsScreen.route
                ){
                    navController.previousBackStackEntry?.savedStateHandle?.get<String?>("url")
                        ?.let { url ->
                            DetailScreen(
                                innerPadding = innerPadding,
                                url = url,
                            )
                        }
                }
            }

        }
    )

}


