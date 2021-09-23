package com.example.ilinkacademy.di

import com.example.ilinkacademy.data.remote.CatsAPI
import com.example.ilinkacademy.data.remote.DucksAPI
import com.example.ilinkacademy.data.repository.CatsRepository
import com.example.ilinkacademy.data.repository.DucksRepository
import com.example.ilinkacademy.data.repository.impl.CatsRepositoryImpl
import com.example.ilinkacademy.data.repository.impl.DucksRepositoryImpl
import com.example.ilinkacademy.utils.Constants.CATS_BASE_URL
import com.example.ilinkacademy.utils.Constants.DUCKS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.sql.CommonDataSource

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val gsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCatsClient(): CatsAPI = Retrofit.Builder().addConverterFactory(gsonConverterFactory)
        .baseUrl(CATS_BASE_URL)
        .build()
        .create(CatsAPI::class.java)

    @Provides
    @Singleton
    fun provideDucksClient(): DucksAPI =
        Retrofit.Builder().addConverterFactory(gsonConverterFactory)
            .baseUrl(DUCKS_BASE_URL)
            .build()
            .create(DucksAPI::class.java)

    @Provides
    @Singleton
    fun provideCatsRepository(catsRemoteDataSource: CatsAPI): CatsRepository = CatsRepositoryImpl(catsRemoteDataSource)

    @Provides
    @Singleton
    fun provideDucksRepository(duckRemoteDataSource: DucksAPI): DucksRepository = DucksRepositoryImpl(duckRemoteDataSource)

}