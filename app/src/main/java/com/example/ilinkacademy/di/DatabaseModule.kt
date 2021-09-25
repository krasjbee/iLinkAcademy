package com.example.ilinkacademy.di

import android.content.Context
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.AnimalPicsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimalPicsDatabase =
        AnimalPicsDatabase.getInstance(context)

    @Provides
    fun provideDao(database: AnimalPicsDatabase): AnimalPicDao = database.animalPicDao()

}