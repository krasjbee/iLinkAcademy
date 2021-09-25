package com.example.ilinkacademy.data.remote

import com.example.ilinkacademy.data.remote.dto.DucksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DucksAPI {

    @GET("random")
    suspend fun getRandomDuck(@Query("type") imageType:String = "jpg"):Response<DucksResponse>

}