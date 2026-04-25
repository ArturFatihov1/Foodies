package com.example.foodies.presentation.screen.products.ui

import androidx.compose.runtime.Composable
import com.example.foodies.presentation.components.ErrorAlert
import com.example.foodies.presentation.components.ProgressIndicator
import com.example.foodies.presentation.screen.products.ProductsIntent
import com.example.foodies.presentation.screen.products.ProductsState

@Composable
fun ProductsScreen(
    state: ProductsState,
    onIntent: (ProductsIntent) -> Unit,
) {

    ProductContent(
        state = state,
        onIntent = onIntent
    )

    if (state.loading)
        ProgressIndicator()

    if (state.showError)
        ErrorAlert(
            message = state.error ?: "",
            onRetryClick = { onIntent(ProductsIntent.LoadProducts) },
            onDismissClick = null
        )
}