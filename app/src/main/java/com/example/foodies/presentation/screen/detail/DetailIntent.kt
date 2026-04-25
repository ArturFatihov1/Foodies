package com.example.foodies.presentation.screen.detail

sealed interface DetailIntent {
    data class LoadProduct(val id: Long) : DetailIntent
    object OnBackClick : DetailIntent
}