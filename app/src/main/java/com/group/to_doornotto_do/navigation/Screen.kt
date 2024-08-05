package com.group.to_doornotto_do.navigation

sealed class Screen (val route: String) {
    data object Home: Screen(route = "home_screen")
    data object ListScreen: Screen(route = "list_screen")
}
