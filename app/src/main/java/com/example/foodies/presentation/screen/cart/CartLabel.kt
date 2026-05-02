package com.example.foodies.presentation.screen.cart

sealed interface CartLabel {
    data object NavigateToBack : CartLabel
    // TODO сделать другие label
}