package com.example.articbrowser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.articbrowser.feature.art_details.ArtDetailsScreen
import com.example.articbrowser.feature.art_grid.ArtGridScreen
import com.example.articbrowser.feature.art_grid.ArtGridViewModel
import com.example.articbrowser.feature.artic_about.AboutScreen
import com.google.firebase.analytics.FirebaseAnalytics


@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            ArtGridScreen(
                onLogoClick = {
                    navController.navigate(Screen.About.route)
                }
            ) { clickedArtId ->
                navController.navigate(Screen.Details.createRoute(clickedArtId))
            }
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("artId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("artId") ?: -1
            ArtDetailsScreen(itemId)
        }
        composable(route = Screen.About.route) {
            AboutScreen()
        }
    }
}