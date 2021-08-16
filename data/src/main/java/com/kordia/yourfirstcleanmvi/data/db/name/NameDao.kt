package com.kordia.yourfirstcleanmvi.data.db.name

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NameDao {

    @Insert
    suspend fun insert(name: NameData): Long

    @Query("SELECT * FROM name_table" )
    suspend fun getAll(): List<NameData>

    @Query("DELETE FROM name_table")
    suspend fun deleteAll()
}