package com.example.foodies.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.foodies.presentation.ProductViewModel
import com.example.foodies.presentation.core.ErrorAlert
import com.example.foodies.presentation.core.ProgressIndicator

@Composable
fun DetailScreen(
    productId: Long,
    viewModel: ProductViewModel,
    onBackClick: () -> Unit,
) {

    val uiState by viewModel.detailState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadDetail(productId)
    }

    when (val state = uiState) {
        is DetailState.Loading -> ProgressIndicator()
        is DetailState.Error -> ErrorAlert(
            message = state.message,
            onRetryClick = { viewModel.loadDetail(productId) },
            onDismissClick = onBackClick
        )
        is DetailState.Success -> DetailContent(product = state.product, onBackClick = onBackClick)
    }
}