package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.repository.DucksRepository
import com.example.ilinkacademy.utils.NetworkResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomDuckUsecase @Inject constructor(private val repository: DucksRepository) {
    operator fun invoke() = flow {
        emit(NetworkResource.Loading())
        val response = repository.getRandomDuck()
        if (response.isSuccessful) {
            val responseBody = response.body() ?: throw Exception("Body is null")
            emit(NetworkResource.Success(responseBody.url))
        } else {
            emit(NetworkResource.Error("Unexpected error"))
        }
    }.catch { e ->
        emit(NetworkResource.Error(e.localizedMessage ?: """¯\_(ツ)_/¯"""))
    }
}