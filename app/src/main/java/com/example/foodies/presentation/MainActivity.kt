package com.example.foodies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.foodies.presentation.feature.ProductsScreen
import com.example.foodies.presentation.theme.FoodiesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: ProductViewModel by viewModel()
        setContent {
            FoodiesTheme {
                ProductsScreen(viewModel)
            }
        }
    }
}