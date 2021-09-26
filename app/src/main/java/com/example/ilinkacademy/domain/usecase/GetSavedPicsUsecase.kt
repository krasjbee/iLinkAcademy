package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.local.AnimalPicDao
import javax.inject.Inject

class GetSavedPicsUsecase @Inject constructor(private val dao: AnimalPicDao) {
    operator fun invoke() = dao.getAllPictures()
}