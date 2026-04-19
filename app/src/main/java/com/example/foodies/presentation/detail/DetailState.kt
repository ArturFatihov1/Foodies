package com.example.foodies.presentation.detail

import com.example.foodies.domain.entities.Product

sealed interface DetailState {
    object Loading : DetailState
    data class Success(val product: Product) : DetailState
    data class Error(val message: String) : DetailState
}