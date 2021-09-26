package com.example.ilinkacademy.data.local

import androidx.room.*
import com.example.ilinkacademy.data.local.dto.AnimalPic
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalPicDao {

    @Query("SELECT * FROM pictures")
    fun getAllPictures(): Flow<List<AnimalPic>>

    @Delete
    fun deletePicture(picture: AnimalPic)

    @Query("DELETE FROM pictures")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: AnimalPic)
}