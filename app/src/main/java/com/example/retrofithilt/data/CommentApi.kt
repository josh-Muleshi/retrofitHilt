package com.example.retrofithilt.data

import com.example.retrofithilt.data.models.CommentResponse
import retrofit2.Response
import retrofit2.http.GET

interface CommentApi {

    @GET("/comments")
    suspend fun getComments():Response<List<CommentResponse>>
}