package com.example.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable()
fun AppNavGraph(
    navHostController: NavHostController,
    productScreenContent: @Composable () -> Unit,
    detailScreenContent: @Composable (Long) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ProductScreen.route
    ) {
        composable(route = Screen.ProductScreen.route) {
            productScreenContent()
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_PRODUCT_ID) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getLong(Screen.KEY_PRODUCT_ID) ?: 0
            detailScreenContent(productId)
        }
    }
}