package com.kordia.yourfirstcleanmvi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kordia.yourfirstcleanmvi.data.db.name.NameDao
import com.kordia.yourfirstcleanmvi.data.db.name.NameData

@Database(entities = [NameData::class], version = 1, exportSchema = false)
abstract class MVIDataBase : RoomDatabase() {

    abstract fun nameDao(): NameDao
}