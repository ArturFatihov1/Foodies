package com.example.foodies.data.di

import com.example.foodies.presentation.screen.cart.CartViewModel
import com.example.foodies.presentation.screen.detail.DetailViewModel
import com.example.foodies.presentation.screen.products.ProductViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::CartViewModel)
}

internal val presentationModule = module {
    includes(viewModelModule)
}
