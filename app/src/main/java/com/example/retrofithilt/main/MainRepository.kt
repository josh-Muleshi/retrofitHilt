package com.example.retrofithilt.main

import com.example.retrofithilt.data.models.CommentResponse
import com.example.retrofithilt.util.Resource

interface MainRepository {
    suspend fun getComments(): Resource<List<CommentResponse>>
}