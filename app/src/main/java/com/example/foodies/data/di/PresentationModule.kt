package com.example.foodies.data.di

import com.example.foodies.presentation.screen.products.ProductViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::ProductViewModel)
}

internal val presentationModule = module {
    includes(viewModelModule)
}
