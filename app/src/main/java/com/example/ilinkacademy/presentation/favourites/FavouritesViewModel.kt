package com.example.ilinkacademy.presentation.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import com.example.ilinkacademy.domain.entities.FavPicture
import com.example.ilinkacademy.domain.usecase.GetSavedPicsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val savedPicsUsecase: GetSavedPicsUsecase,
    private val dao: AnimalPicDao
) :
    ViewModel() {
    init {
        viewModelScope.launch {
            delay(5000)
            dao.deleteAll()
        }
    }

    // TODO: 26.09.2021 add adapter and load images
    val savedPiscLiveData =
        savedPicsUsecase().transform<List<AnimalPic>, List<FavPicture>> { list ->

        }.asLiveData().also { Log.d("qwe", ":qweqweq ") }
}