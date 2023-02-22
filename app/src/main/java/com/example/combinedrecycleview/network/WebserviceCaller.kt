package com.example.combinedrecycleview.network

import com.example.combinedrecycleview.CraterParsers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebserviceCaller {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("https://testapi.blujobs.in/")
            .addConverterFactory(GsonConverterFactory.create(CraterParsers.gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    @JvmStatic
    fun getClient(): ApiService = apiService

}