package com.halic.ilerimobilfinal.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halic.ilerimobilfinal.persistence.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}