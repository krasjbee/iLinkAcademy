package com.example.ilinkacademy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ilinkacademy.data.local.dto.AnimalPic

@Database(entities = [AnimalPic::class], version = 1)
abstract class AnimalPicsDatabase : RoomDatabase() {
    abstract fun animalPicDao(): AnimalPicDao

    companion object {
        @Volatile
        private var database: AnimalPicsDatabase? = null

        fun getInstance(context: Context): AnimalPicsDatabase {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(
                    context,
                    AnimalPicsDatabase::class.java,
                    "animal_pics_db"
                ).build()
            }
        }

    }
}