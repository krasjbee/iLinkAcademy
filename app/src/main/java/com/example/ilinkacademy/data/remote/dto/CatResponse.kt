package com.example.ilinkacademy.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("webpurl")
    val webpurl: String,
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double
)