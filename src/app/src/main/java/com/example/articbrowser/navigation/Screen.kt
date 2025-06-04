package com.example.articbrowser.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Details : Screen("details/{artId}") {
        fun createRoute(artId: Int): String = "details/$artId"
    }
    data object About : Screen("about")
}