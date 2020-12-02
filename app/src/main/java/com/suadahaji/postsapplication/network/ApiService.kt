package com.suadahaji.postsapplication.network

import com.suadahaji.postsapplication.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}