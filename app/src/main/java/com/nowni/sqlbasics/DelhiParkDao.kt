package com.nowni.sqlbasics

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DelhiParkDao {
    @Insert
    suspend fun insertAll(parks: List<DelhiPark>)
    @Query("SELECT * FROM park")
    suspend fun getAll(): List<DelhiPark>

}