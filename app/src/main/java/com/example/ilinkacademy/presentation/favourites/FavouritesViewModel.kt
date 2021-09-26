package com.example.ilinkacademy.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import com.example.ilinkacademy.domain.usecase.GetSavedPicsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val savedPicsUsecase: GetSavedPicsUsecase,
    private val dao: AnimalPicDao
) :
    ViewModel() {

    // TODO: 26.09.2021 add adapter and load images
    val savedPiscLiveData = dao.getAllPictures().asLiveData()

    fun deleteElement(animalPic: AnimalPic) {
        viewModelScope.launch { dao.deletePicture(animalPic) }
    }
}