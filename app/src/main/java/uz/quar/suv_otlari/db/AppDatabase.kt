package uz.quar.suv_otlari.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.quar.suv_otlari.models.WordTable

@Database(entities = [WordTable::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "data.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("data.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
