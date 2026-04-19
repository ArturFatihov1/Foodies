package com.example.foodies.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.foodies.presentation.ProductViewModel
import com.example.foodies.presentation.core.ErrorAlert

@OptIn(ExperimentalMaterial3Api::class)
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
        is DetailState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFFF15412))
        }

        is DetailState.Error -> ErrorAlert(
            message = state.message,
            viewModel = viewModel,
            productId = productId,
            onBackClick = onBackClick
        )

        is DetailState.Success -> DetailContent(product = state.product, onBackClick = onBackClick)
    }
}

