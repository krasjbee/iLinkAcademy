package com.example.ilinkacademy.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream

fun Drawable.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

fun ByteArray.toDrawable(): Drawable {
    val steam = this.inputStream()
    return Drawable.createFromStream(steam, null)
}