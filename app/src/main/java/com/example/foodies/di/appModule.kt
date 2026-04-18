package com.example.foodies.di

import com.example.foodies.data.CloudRepository
import com.example.foodies.data.ProductsRepositoryImpl
import com.example.foodies.domain.ProductsRepository
import com.example.foodies.presentation.ProductViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(CloudRepository::class.java) }

    single<ProductsRepository> {
        ProductsRepositoryImpl(get())
    }
    viewModel {
        ProductViewModel(get())
    }
}