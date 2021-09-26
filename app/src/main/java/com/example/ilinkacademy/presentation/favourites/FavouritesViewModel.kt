package com.example.ilinkacademy.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val dao: AnimalPicDao
) :
    ViewModel() {

    val savedPiscLiveData = dao.getAllPictures().asLiveData()

    fun deleteElement(animalPic: AnimalPic) {
        viewModelScope.launch { dao.deletePicture(animalPic) }
    }
}