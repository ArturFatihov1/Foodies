package com.example.foodies.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.foodies.presentation.screen.Detail
import com.example.foodies.presentation.screen.ProductList
import com.example.foodies.presentation.screen.detail.DetailScreen
import com.example.foodies.presentation.screen.products.ProductsScreen

@Composable()
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ProductList
    ) {
        composable<ProductList> {
            ProductsScreen(
                onCardClick = { product ->
                    navController.navigate(Detail(productId = product.id))
                }
            )
        }
        composable<Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<Detail>()
            DetailScreen(
                productId = args.productId,
                onBackClick = { navController.navigateUp() }
            )
        }
    }

}