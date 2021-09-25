package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.data.repository.CatsRepository
import com.example.ilinkacademy.utils.NetworkResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomCatUsecase @Inject constructor(val repository: CatsRepository) :
    AbstractRandomAnimalUsecase() {
    override operator fun invoke() = flow {
        emit(NetworkResource.Loading())
        val response = repository.getRandomCat()
        if (response.isSuccessful) {
            val responseBody = response.body() ?: throw Exception("Body is null")
            emit(NetworkResource.Success(responseBody.webpurl))
        } else {
            emit(NetworkResource.Error("Unable to get picture"))
        }
    }.catch { e ->
        emit(NetworkResource.Error(e.localizedMessage ?: "Unexpected error" + """¯\_(ツ)_/¯"""))
    }
}