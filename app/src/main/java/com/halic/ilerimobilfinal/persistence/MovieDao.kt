package com.halic.ilerimobilfinal.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halic.ilerimobilfinal.persistence.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getAll(): List<MovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)
}