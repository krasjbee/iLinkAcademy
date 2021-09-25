package com.example.ilinkacademy.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DucksResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("url")
    val url: String
)