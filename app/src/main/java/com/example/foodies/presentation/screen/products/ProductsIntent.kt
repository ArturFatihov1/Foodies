package com.example.foodies.presentation.screen.products


sealed interface ProductsIntent {
    object LoadProducts : ProductsIntent
    data class OnProductClick(val id: Long) : ProductsIntent
    data class SelectedCategory(val category: String) : ProductsIntent
    data class ChangeHeader(val choice: Boolean) : ProductsIntent
    data class OnQueryChanged(val query: String) : ProductsIntent
}