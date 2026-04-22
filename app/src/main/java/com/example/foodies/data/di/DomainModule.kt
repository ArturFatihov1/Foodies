package com.example.foodies.data.di

import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetCategoriesProductUseCase
import com.example.foodies.domain.GetDetailProductUseCase
import com.example.foodies.domain.SearchProductUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val useCaseModule = module {
    singleOf(::GetAllProductsUseCase)
    singleOf(::GetDetailProductUseCase)
    singleOf(::GetCategoriesProductUseCase)
    singleOf(::SearchProductUseCase)
}
internal val domainModule = module {
    includes(useCaseModule)
}