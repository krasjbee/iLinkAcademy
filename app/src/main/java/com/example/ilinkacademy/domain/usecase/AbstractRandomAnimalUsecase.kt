package com.example.ilinkacademy.domain.usecase

import com.example.ilinkacademy.utils.NetworkResource
import kotlinx.coroutines.flow.Flow

abstract class AbstractRandomAnimalUsecase {
    abstract operator fun invoke(): Flow<NetworkResource<String>>
}