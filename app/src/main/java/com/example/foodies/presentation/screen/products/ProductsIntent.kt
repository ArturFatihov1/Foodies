package com.example.foodies.presentation.screen.products

import com.example.foodies.domain.entities.Product


sealed interface ProductsIntent {
    object LoadProducts : ProductsIntent
    data class OnProductClick(val id: Long) : ProductsIntent
    data class SelectedCategory(val category: String) : ProductsIntent
    data class ChangeHeader(val choice: Boolean) : ProductsIntent
    data class OnQueryChanged(val query: String) : ProductsIntent
    data class AddToCartProduct(val product: Product) : ProductsIntent
    data class IncrementQuantity(val product: Product) : ProductsIntent
    data class DecrementQuantity(val product: Product) : ProductsIntent
    data object NavigateToCart : ProductsIntent
}