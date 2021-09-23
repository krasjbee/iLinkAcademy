package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.dto.CatResponse
import com.example.ilinkacademy.data.dto.DucksResponse
import com.example.ilinkacademy.data.repository.DucksRepository
import com.example.ilinkacademy.utils.NetworkResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomDuckUsecase @Inject constructor(private val repository : DucksRepository) {
    suspend operator fun invoke() = flow {
        emit(NetworkResource.Loading())
        val response = repository.getRandomDuck()
        if (response.isSuccessful) {
            val responseBody = response.body() ?: throw Exception("Body is null")
            emit(NetworkResource.Success(responseBody))
        } else {
            emit(NetworkResource.Error("Unexpected error"))
        }
    }.catch { e ->
        emit(NetworkResource.Error(e.localizedMessage ?: """¯\_(ツ)_/¯"""))
    }
}