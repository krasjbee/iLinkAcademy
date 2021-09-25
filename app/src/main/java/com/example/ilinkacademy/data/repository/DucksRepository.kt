package com.example.ilinkacademy.data.repository

import com.example.ilinkacademy.data.remote.dto.DucksResponse
import retrofit2.Response

interface DucksRepository {
    suspend fun getRandomDuck() : Response<DucksResponse>
}