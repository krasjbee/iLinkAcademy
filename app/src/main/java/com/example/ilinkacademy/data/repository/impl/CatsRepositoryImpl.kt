package com.example.ilinkacademy.data.repository.impl

import com.example.ilinkacademy.data.remote.CatsAPI
import com.example.ilinkacademy.data.repository.CatsRepository
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(private val catRemoteDataSource: CatsAPI) :
    CatsRepository {
    override suspend fun getRandomCat() = catRemoteDataSource.getRandomCat()
}