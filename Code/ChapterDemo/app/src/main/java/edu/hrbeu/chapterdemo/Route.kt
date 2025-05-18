package edu.hrbeu.chapterdemo

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
) {

    object HomeScreen : Route(route = "homeScreen")

    object SettingScreen : Route(route = "settingScreen")

    object HistoryScreen : Route(route = "historyScreen")

    object DetailsScreen : Route(route = "detailScreen")
}