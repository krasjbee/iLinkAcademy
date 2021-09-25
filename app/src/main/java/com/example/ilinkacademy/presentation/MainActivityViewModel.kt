package com.example.ilinkacademy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestManager
import com.example.ilinkacademy.domain.usecase.AbstractRandomAnimalUsecase
import com.example.ilinkacademy.domain.usecase.RandomCatUsecase
import com.example.ilinkacademy.domain.usecase.RandomDuckUsecase
import com.example.ilinkacademy.utils.NetworkResource
import com.example.ilinkacademy.utils.PictureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val randomCatUsecase: RandomCatUsecase,
    private val randomDuckUsecase: RandomDuckUsecase,
    private val glide: RequestManager
) : ViewModel() {

    private val _pictureLiveData: MutableLiveData<PictureState> =
        MutableLiveData(PictureState.Initial)
    val pictureLiveData: LiveData<PictureState> = _pictureLiveData

    fun getRandomAnimal(animal: Animal) {
        val usecase: AbstractRandomAnimalUsecase = when (animal) {
            Animal.DUCK -> randomDuckUsecase
            Animal.CAT -> randomCatUsecase
        }
        usecase().onEach { resource ->
            when (resource) {
                is NetworkResource.Loading -> {
                    _pictureLiveData.postValue(PictureState.Loading)
                }
                is NetworkResource.Success -> {
                    //await for animation (possible)
                    val qwe = viewModelScope.async(Dispatchers.IO) {
                        glide.load(resource.data).submit().get()
                    }
                    _pictureLiveData.postValue(PictureState.Success(qwe.await()))

                }
                is NetworkResource.Error -> {
                    _pictureLiveData.postValue(PictureState.Error(resource.message!!))
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        enum class Animal { DUCK, CAT }
    }
}