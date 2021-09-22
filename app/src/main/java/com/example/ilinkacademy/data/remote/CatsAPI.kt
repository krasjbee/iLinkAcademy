package com.example.ilinkacademy.data.remote

import com.example.ilinkacademy.data.dto.CatResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatsAPI {

    @GET("rest")
    suspend fun getRandomCat() : Response<CatResponse>

}