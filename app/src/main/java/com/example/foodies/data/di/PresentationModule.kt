package com.example.foodies.data.di

import com.example.foodies.presentation.screen.detail.DetailViewModel
import com.example.foodies.presentation.screen.products.ProductViewModel
import com.example.foodies.presentation.screen.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailViewModel)
}

internal val presentationModule = module {
    includes(viewModelModule)
}
