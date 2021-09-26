package com.example.ilinkacademy.presentation.randomPicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestManager
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.domain.usecase.AbstractRandomAnimalUsecase
import com.example.ilinkacademy.domain.usecase.RandomCatUsecase
import com.example.ilinkacademy.domain.usecase.RandomDuckUsecase
import com.example.ilinkacademy.domain.usecase.SaveToDatabaseUsecase
import com.example.ilinkacademy.utils.NetworkResource
import com.example.ilinkacademy.utils.PictureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomPictureViewModel @Inject constructor(
    private val randomCatUsecase: RandomCatUsecase,
    private val randomDuckUsecase: RandomDuckUsecase,
    private val glideRequestManager: RequestManager,
    private val saver: SaveToDatabaseUsecase,
    private val dao: AnimalPicDao
) : ViewModel() {

    private val _pictureLiveData: MutableLiveData<PictureState> =
        MutableLiveData(PictureState.Initial)
    val pictureLiveData: LiveData<PictureState> = _pictureLiveData
    private var favList: List<String>? = null

    /**
     * returns is element in favourites
     */
    private fun isFavourite(url: String): Boolean? {
        dao.getAllPictures().onEach { list ->
            favList = list.map { it.url }
        }.launchIn(viewModelScope)
        return favList?.contains(url)
    }

    /**
     * saves to database file uri using url as primary key
     */
    fun saveToFavourites(uri: String, url: String) {
        viewModelScope.launch {
            saver(
                uri = uri, url = url
            )
        }
    }

    /**
     * Gets random picture of animal
     */
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
                    val image = viewModelScope.async(Dispatchers.IO) {
                        glideRequestManager.load(resource.data).submit().get()
                    }
                    _pictureLiveData.postValue(
                        PictureState.Success(
                            image.await(),
                            resource.data!!,
                            isFavourite(resource.data)
                        )
                    )
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