package com.example.ilinkacademy.domain.usecase

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import com.example.ilinkacademy.data.local.AnimalPicDao
import com.example.ilinkacademy.data.local.dto.AnimalPic
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class SaveToDatabaseUsecase @Inject constructor(private val dao: AnimalPicDao) {
    suspend operator fun invoke(picture: Drawable, url: String) {
        var stream = ByteArrayOutputStream()
        val pic = picture.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()
        val animalPic = AnimalPic(url, byteArray)
        dao.insertPicture(animalPic)
    }
}