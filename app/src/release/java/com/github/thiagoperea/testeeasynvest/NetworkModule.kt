package com.github.thiagoperea.testeeasynvest

import com.github.thiagoperea.testeeasynvest.data.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api-simulator-calc.easynvest.com.br")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}