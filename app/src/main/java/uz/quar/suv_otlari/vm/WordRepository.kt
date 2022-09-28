package uz.quar.suv_otlari.vm

import android.app.Application
import uz.quar.suv_otlari.db.AppDatabase
import uz.quar.suv_otlari.models.WordTable


class WordRepository(application: Application) {
    private val appDao = AppDatabase.getDatabase(application).appDao()

    suspend fun getWordAll(): List<WordTable> {
        return appDao.getDicAll()
    }

    fun getWord(id: Int): WordTable {
        return appDao.getDic(id)
    }


}
