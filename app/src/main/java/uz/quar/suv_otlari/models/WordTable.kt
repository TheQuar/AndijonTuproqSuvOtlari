package uz.quar.suv_otlari.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usimlik")
data class WordTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kingdom: String,
    val phylum: String,
    val sinf: String,
    val order: String,
    val family: String,
    val genus: String,
    val species: String,
    val dateidentified: String,
    val dentifiedby: String,
    val yearidentified: String,
    val collectornember: String,
    val continent: String,
    val country: String,
    val soil: String,
    val region: String,
    val seasonaloccurrence: String,
    val eighteen: String,
    val tur: String,
    val olim: String
)
