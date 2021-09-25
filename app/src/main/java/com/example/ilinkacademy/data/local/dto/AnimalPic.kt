package com.example.ilinkacademy.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures")
data class AnimalPic(
    @PrimaryKey
    val url: String,
    val image: ByteArray
)