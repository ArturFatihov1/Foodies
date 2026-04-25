package com.example.foodies.presentation.screen.detail

import com.example.foodies.domain.entities.Product

data class DetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val messageError: String? = null
)