package com.example.foodies.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.foodies.presentation.components.ErrorAlert
import com.example.foodies.presentation.components.ProgressIndicator
import com.example.foodies.presentation.screen.products.ProductViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    productId: Long,
    viewModel: ProductViewModel = koinViewModel(),
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