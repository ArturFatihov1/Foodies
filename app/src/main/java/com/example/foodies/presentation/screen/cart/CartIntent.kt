package com.example.foodies.presentation.screen.cart

import com.example.foodies.domain.entities.Product

sealed interface CartIntent {
    data class IncrementQuantity(val product: Product) : CartIntent
    data class DecrementQuantity(val product: Product) : CartIntent
    data object NavigateToBack : CartIntent
}