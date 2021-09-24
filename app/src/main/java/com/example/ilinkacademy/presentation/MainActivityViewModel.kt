package com.example.ilinkacademy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilinkacademy.domain.usecase.RandomCatUsecase
import com.example.ilinkacademy.domain.usecase.RandomDuckUsecase
import com.example.ilinkacademy.utils.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val randomCatUsecase: RandomCatUsecase,
    private val randomDuckUsecase: RandomDuckUsecase
) : ViewModel() {

    private val _pictureLiveData: MutableLiveData<String> = MutableLiveData("")
    val pictureLiveData: LiveData<String> = _pictureLiveData

    fun getRandomDuck() {
        randomDuckUsecase().onEach { response ->
            when (response) {
                is NetworkResource.Loading -> {
                }
                is NetworkResource.Success -> {
                    response.data
                }
                is NetworkResource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRandomCat() {
        randomCatUsecase().onEach { resource ->
            when (resource) {
                is NetworkResource.Loading -> {
                    _pictureLiveData.postValue("Loading")
                }
                is NetworkResource.Success -> {
                    _pictureLiveData.postValue("success ")
                }
                is NetworkResource.Error -> {
                    _pictureLiveData.postValue("error ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)

    }
}