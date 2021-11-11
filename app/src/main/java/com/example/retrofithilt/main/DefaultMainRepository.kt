package com.example.retrofithilt.main

import com.example.retrofithilt.data.CommentApi
import com.example.retrofithilt.data.models.CommentResponse
import com.example.retrofithilt.util.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CommentApi
) : MainRepository {

    override suspend fun getComments(): Resource<List<CommentResponse>> {
        return try {
            val response = api.getComments()
            val result = response.body()

            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else{
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}