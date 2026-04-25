package com.example.foodies.presentation.screen.products

sealed interface ProductsLabel {
    data class NavigateToDetail(val id: Long) : ProductsLabel
}
