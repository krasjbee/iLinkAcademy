package com.example.ilinkacademy.data.local

import androidx.room.*
import com.example.ilinkacademy.data.local.dto.AnimalPic

@Dao
interface AnimalPicDao {

    @Query("SELECT * FROM pictures")
    fun getAllPictures(): List<AnimalPic>

    @Delete
    fun deletePicture(picture: AnimalPic)

    @Query("DELETE FROM pictures")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: AnimalPic)
}