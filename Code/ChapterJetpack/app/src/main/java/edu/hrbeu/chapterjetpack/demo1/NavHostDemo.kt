package edu.hrbeu.chapterjetpack.demo1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.hrbeu.chapterjetpack.ui.theme.ChapterJetpackTheme


class NavHostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterJetpackTheme {

                NavHostDemo()
            }
        }
    }
}

@Preview
@Composable
fun NavHostDemo() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a"){
        composable(
            route = "screen_a"
        ){
            ScreenA(
                navigateToB = {
                    navController.navigate("screen_b")
                }
            )
        }

        composable(
            route = "screen_b"
        ){
            ScreenB(
                navigateToC = {
                    navController.navigate("screen_c")
                },
                navigateToA = {
                    navController.popBackStack()
                },
            )
        }

        composable(
            route = "screen_c"
        ){
            ScreenC(
                navigateToB = {
                    navController.popBackStack()
                },
                navigateToA = {
                    navController.popBackStack()
                    navController.popBackStack()
                },
            )
        }
    }
}
