package com.example.foodies.presentation.screen.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.foodies.presentation.components.ErrorAlert
import com.example.foodies.presentation.components.ProgressIndicator
import com.example.foodies.presentation.screen.detail.DetailIntent
import com.example.foodies.presentation.screen.detail.DetailState

@Composable
fun DetailScreen(
    productId: Long,
    state: DetailState,
    onIntent: (DetailIntent) -> Unit,
) {


    LaunchedEffect(productId) {
        onIntent(DetailIntent.LoadProduct(productId))
    }

    if (state.isLoading)
        ProgressIndicator()
    if (state.product != null)
        DetailContent(product = state.product, onIntent = onIntent)
    if (state.messageError != null && !state.isLoading)
        ErrorAlert(
            message = state.messageError,
            onRetryClick = { onIntent(DetailIntent.LoadProduct(productId)) },
            onDismissClick = { onIntent(DetailIntent.OnBackClick) }
        )
}