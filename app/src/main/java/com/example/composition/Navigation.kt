package com.example.composition

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.composition.collection.CollectionScreen
import com.example.composition.history.HistoryScreen
import com.example.composition.home.HomeScreen

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Collection: Screen("collection")
    object History: Screen("history")
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(Screen.Home.route) {
        HomeScreen(
            onExit = {},
            navigateToCollection = { navController.navigate(Screen.Collection.route) },
            navigationToHistory = { navController.navigate(Screen.History.route) },
            changePeriod = {}
        )
    }

    composable(Screen.Collection.route) {
        CollectionScreen(
            onBack = { navController.popBackStack(Screen.Home.route, inclusive = false) }
        )
    }

    composable(Screen.History.route) {
        HistoryScreen(
            onBack = { navController.popBackStack(Screen.Home.route, inclusive = false) }
        )
    }
}