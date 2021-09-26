package com.example.ilinkacademy.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream

/**
 * converts drawable to ByteArray
 */
fun Drawable.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

