package com.halic.ilerimobilfinal.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val release: String,
    @ColumnInfo val playtime: String,
    @ColumnInfo val description: String,
    @ColumnInfo val plot: String,
    @ColumnInfo val poster: String
)
