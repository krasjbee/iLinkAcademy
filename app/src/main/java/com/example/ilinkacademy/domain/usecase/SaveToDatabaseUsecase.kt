package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import javax.inject.Inject

class SaveToDatabaseUsecase @Inject constructor(private val dao: AnimalPicDao) {
    suspend operator fun invoke(byteArray: ByteArray, url: String) {
        val animalPic = AnimalPic(url, byteArray)
        dao.insertPicture(animalPic)
    }
}