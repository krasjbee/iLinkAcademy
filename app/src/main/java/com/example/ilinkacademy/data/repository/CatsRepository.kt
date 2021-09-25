package com.example.ilinkacademy.data.repository

import com.example.ilinkacademy.data.remote.dto.CatResponse
import retrofit2.Response

interface CatsRepository {
    suspend fun getRandomCat(): Response<CatResponse>
}