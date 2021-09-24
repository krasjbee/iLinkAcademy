package com.example.ilinkacademy.di

import com.example.ilinkacademy.data.repository.CatsRepository
import com.example.ilinkacademy.data.repository.DucksRepository
import com.example.ilinkacademy.domain.usecase.RandomCatUsecase
import com.example.ilinkacademy.domain.usecase.RandomDuckUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UsecaseModule {

    @Provides
    @ViewModelScoped
    fun provideRandomDuckUsecase(repository: DucksRepository): RandomDuckUsecase =
        RandomDuckUsecase(repository)

    @Provides
    @ViewModelScoped
    fun provideRandomCatUsecase(repository: CatsRepository): RandomCatUsecase =
        RandomCatUsecase(repository)
}