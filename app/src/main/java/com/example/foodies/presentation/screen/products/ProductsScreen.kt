package com.example.foodies.presentation.screen.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.foodies.domain.entities.Product
import com.example.foodies.presentation.components.ErrorAlert
import com.example.foodies.presentation.components.ProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductViewModel = koinViewModel(),
    onCardClick: (Product) -> Unit
) {
    val uiState by viewModel.productState.collectAsState()

    when (val state = uiState) {
        is ProductState.Loading -> ProgressIndicator()
        is ProductState.Error -> ErrorAlert(
            message = state.message,
            onRetryClick = { viewModel.load() },
            onDismissClick = null
        )

        is ProductState.Success -> ProductContent(
            viewModel,
            state = state,
            onCardClick = onCardClick
        )
        else -> {
            ProductState.Idle
        }
    }
}