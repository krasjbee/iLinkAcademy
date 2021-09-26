package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import javax.inject.Inject

class SaveToDatabaseUsecase @Inject constructor(private val dao: AnimalPicDao) {
    suspend operator fun invoke(uri: String, url: String) {
        val animalPic = AnimalPic(url = url, imageUri = uri)
        dao.insertPicture(animalPic)
    }
}