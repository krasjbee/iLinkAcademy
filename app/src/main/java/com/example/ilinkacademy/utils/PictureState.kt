package com.example.ilinkacademy.utils

import android.graphics.drawable.Drawable

sealed class PictureState {
    object Initial : PictureState()
    object Loading : PictureState()
    class Success(val drawable: Drawable) : PictureState()
    class Error(val error: String) : PictureState()
}
