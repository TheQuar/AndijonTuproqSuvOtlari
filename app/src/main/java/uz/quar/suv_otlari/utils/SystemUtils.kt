package uz.quar.suv_otlari.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

class SystemUtils {
    companion object {
        fun openImage(context: Context, name: String): Bitmap? {
            return try {
                BitmapFactory.decodeStream(context.assets.open(name))
            } catch (e: Exception) {
                BitmapFactory.decodeStream(context.assets.open("no_image.jpg"))
            }
        }
    }
}