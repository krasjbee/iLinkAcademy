package com.example.ilinkacademy.di

import com.example.ilinkacademy.data.remote.CatsAPI
import com.example.ilinkacademy.data.remote.DucksAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val gsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCatsClient() = Retrofit.Builder().addConverterFactory(gsonConverterFactory)
        .baseUrl("https://thatcopy.pw/catapi/")
        .build()
        .create(CatsAPI::class.java)

    @Provides
    @Singleton
    fun provideDucksClient() = Retrofit.Builder().addConverterFactory(gsonConverterFactory)
        .baseUrl("https://random-d.uk/api/")
        .build()
        .create(DucksAPI::class.java)

}