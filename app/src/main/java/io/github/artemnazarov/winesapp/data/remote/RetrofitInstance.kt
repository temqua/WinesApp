package io.github.artemnazarov.winesapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val instance: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.sampleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}