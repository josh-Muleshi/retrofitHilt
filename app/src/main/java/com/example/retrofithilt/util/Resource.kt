package com.example.retrofithilt.util

import com.example.retrofithilt.data.models.CommentResponse

sealed class Resource<T>(val data: T?, val message: String?) : List<CommentResponse> {
    class Success<T>(data: T?): Resource<T>(data, null)
    class Error<T>(message: String?): Resource<T>(null, message)
}