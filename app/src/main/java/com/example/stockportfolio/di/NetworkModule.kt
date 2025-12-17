package com.example.stockportfolio.di

import com.example.stockportfolio.data.remote.HoldingsApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<HoldingsApi> {
        get<Retrofit>().create(HoldingsApi::class.java)
    }
}
