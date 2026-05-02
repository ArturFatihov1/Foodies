package com.example.foodies.data.di

import androidx.room.Room
import com.example.foodies.data.local_source.ProductsDao
import com.example.foodies.data.local_source.ProductsDatabase
import com.example.foodies.data.remote_source.RemoteDataSource
import com.example.foodies.data.repository.CartRepositoryImpl
import com.example.foodies.data.repository.ProductsRepositoryImpl
import com.example.foodies.domain.CartRepository
import com.example.foodies.domain.ProductsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val roomModule = module {
    single<ProductsDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = ProductsDatabase::class.java,
            name = ProductsDatabase.NAME
        ).build()
    }
    single<ProductsDao> { get<ProductsDatabase>().dao }
}

private val retrofitModule = module {

    val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }).readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()


    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

private val repositoryModule = module {
    single { get<Retrofit>().create(RemoteDataSource::class.java) }

    single<ProductsRepository> { ProductsRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
}

internal val dataModule = module {
    includes(
        retrofitModule,
        roomModule,
        repositoryModule
    )
}