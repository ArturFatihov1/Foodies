package com.example.foodies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.foodies.navigation.AppNavGraph
import com.example.foodies.navigation.rememberNavigationState
import com.example.foodies.presentation.detail.DetailScreen
import com.example.foodies.presentation.products.ProductsScreen
import com.example.foodies.presentation.theme.FoodiesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: ProductViewModel by viewModel()
        setContent {
            FoodiesTheme {
                val navigationState = rememberNavigationState()

                AppNavGraph(
                    navHostController = navigationState.navHostController,
                    productScreenContent = {
                        ProductsScreen(
                            viewModel = viewModel,
                            onCardClick = { product ->
                                navigationState.navigateToDetail(product.id)
                            }
                        )
                    },
                    detailScreenContent = { productId ->
                        DetailScreen(
                            productId = productId,
                            viewModel = viewModel,
                            onBackClick = { navigationState.navHostController.popBackStack() }
                        )
                    }
                )
            }
        }
    }
}