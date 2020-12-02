package com.suadahaji.postsapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.suadahaji.postsapplication.utils.POSTS_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    val apiService : ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(POSTS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return@lazy retrofit.create(ApiService::class.java)
    }
}