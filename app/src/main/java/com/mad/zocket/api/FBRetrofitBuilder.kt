package com.mad.zocket.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FBRetrofitBuilder {

    private const val BASE_URL = "https://graph.facebook.com/v11.0/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}