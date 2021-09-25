package com.example.ilinkacademy.data.repository.impl

import com.example.ilinkacademy.data.remote.DucksAPI
import com.example.ilinkacademy.data.remote.dto.DucksResponse
import com.example.ilinkacademy.data.repository.DucksRepository
import retrofit2.Response
import javax.inject.Inject

class DucksRepositoryImpl @Inject constructor(private val duckRemoteDataSource : DucksAPI) : DucksRepository {
    override suspend fun getRandomDuck(): Response<DucksResponse> = duckRemoteDataSource.getRandomDuck()
}