package com.sample.cook.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.FileDescriptor

@SuppressLint("DefaultLocale")
fun String.toUnderscore() = this.replace(" ", "_").toLowerCase()

fun Uri.toBitmap(context: Context): Bitmap {
    val parcelFileDescriptor: ParcelFileDescriptor =
        context.contentResolver.openFileDescriptor(this, "r")!!
    val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
    val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
    parcelFileDescriptor.close()
    return image
}