package com.example.ilinkacademy.utils

import android.graphics.drawable.Drawable

sealed class PictureState {
    object Initial : PictureState()
    object Loading : PictureState()
    class Success(val drawable: Drawable, val url: String, var isFav: Boolean? = false) :
        PictureState()

    class Error(val error: String) : PictureState()
}
