package com.nowni.sqlbasics

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "park")
data class DelhiPark(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "area_acres") val acres: Int,
    @ColumnInfo(name = "park_visitors") val visitors: Int?,
    @ColumnInfo(name = "established") val establishedYear: Long,
    @ColumnInfo(name = "type") val type:String
)
