package com.github.thiagoperea.testeeasynvest

import com.github.thiagoperea.testeeasynvest.data.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api-simulator-calc.easynvest.com.br")
            .build()
            .create(ApiService::class.java)
    }
}