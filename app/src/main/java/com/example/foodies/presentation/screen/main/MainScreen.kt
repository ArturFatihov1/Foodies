package com.example.foodies.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.foodies.presentation.screen.Cart
import com.example.foodies.presentation.screen.Detail
import com.example.foodies.presentation.screen.ProductList
import com.example.foodies.presentation.screen.cart.CartLabel
import com.example.foodies.presentation.screen.cart.CartScreen
import com.example.foodies.presentation.screen.cart.CartViewModel
import com.example.foodies.presentation.screen.detail.DetailLabel
import com.example.foodies.presentation.screen.detail.DetailViewModel
import com.example.foodies.presentation.screen.detail.ui.DetailScreen
import com.example.foodies.presentation.screen.products.ProductViewModel
import com.example.foodies.presentation.screen.products.ProductsLabel
import com.example.foodies.presentation.screen.products.ui.ProductsScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable()
fun MainScreen(
    productViewModel: ProductViewModel = koinViewModel(),
    detailViewModel: DetailViewModel = koinViewModel(),
    cartViewModel: CartViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val state by productViewModel.state.collectAsStateWithLifecycle()
    val detailState by detailViewModel.state.collectAsStateWithLifecycle()
    val cartState by cartViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        launch {
            productViewModel.label.collect { label ->
                when (label) {
                    is ProductsLabel.NavigateToDetail -> navController.navigate(Detail(label.id))
                    ProductsLabel.NavigateToCart -> navController.navigate(Cart)
                }
            }
        }
        launch {
            detailViewModel.label.collect { label ->
                when (label) {
                    is DetailLabel.NavigateToBack -> navController.popBackStack()
                }
            }
        }
        launch {
            cartViewModel.label.collect { label ->
                when (label) {
                    is CartLabel.NavigateToBack -> navController.popBackStack()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = ProductList
    ) {
        composable<ProductList> {
            ProductsScreen(
                state = state,
                onIntent = productViewModel::onIntent,
            )
        }
        composable<Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<Detail>()
            DetailScreen(
                productId = args.productId,
                state = detailState,
                onIntent = detailViewModel::onIntent,
            )
        }
        composable<Cart> {
            CartScreen(
                state = cartState,
                onIntent = cartViewModel::onIntent
            )
        }
    }
}