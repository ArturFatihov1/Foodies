package com.example.foodies.data.di

import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetCategoriesProductUseCase
import com.example.foodies.domain.GetDetailProductUseCase
import com.example.foodies.domain.SearchProductUseCase
import com.example.foodies.domain.feature.cart.AddToCartUseCase
import com.example.foodies.domain.feature.cart.DecrementCartUseCase
import com.example.foodies.domain.feature.cart.GetAllCartUseCase
import com.example.foodies.domain.feature.cart.IncrementCartUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val useCaseModule = module {
    singleOf(::GetAllProductsUseCase)
    singleOf(::GetDetailProductUseCase)
    singleOf(::GetCategoriesProductUseCase)
    singleOf(::SearchProductUseCase)
    singleOf(::GetAllCartUseCase)
    singleOf(::AddToCartUseCase)
    singleOf(::IncrementCartUseCase)
    singleOf(::DecrementCartUseCase)
}
internal val domainModule = module {
    includes(useCaseModule)
}