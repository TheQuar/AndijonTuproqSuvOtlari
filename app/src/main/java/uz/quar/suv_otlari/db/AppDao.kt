package uz.quar.suv_otlari.db

import androidx.room.Dao
import androidx.room.Query
import uz.quar.suv_otlari.models.WordTable

@Dao
interface AppDao {

    @Query("SELECT * FROM usimlik")
    suspend fun getDicAll(): List<WordTable>


    @Query("SELECT * FROM usimlik WHERE id=:id")
    fun getDic(id: Int): WordTable


}