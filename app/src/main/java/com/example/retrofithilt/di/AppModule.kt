package com.example.retrofithilt.di

import com.example.retrofithilt.data.CommentApi
import com.example.retrofithilt.main.DefaultMainRepository
import com.example.retrofithilt.main.MainRepository
import com.example.retrofithilt.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCommentApi() : CommentApi = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CommentApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: CommentApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}