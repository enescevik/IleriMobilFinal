package com.halic.ilerimobilfinal.extensions

import com.halic.ilerimobilfinal.model.MovieModel
import com.halic.ilerimobilfinal.persistence.entities.MovieEntity

fun List<MovieModel>.toEntity(): List<MovieEntity> {
    return this.map { it.toEntity() }
}

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        name = this.name,
        release = this.release,
        playtime = this.playtime,
        description = this.description,
        plot = this.plot,
        poster = this.poster
    )
}

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = this.id,
        name = this.name,
        release = this.release,
        playtime = this.playtime,
        description = this.description,
        plot = this.plot,
        poster = this.poster
    )
}

fun List<MovieEntity>.toModel(): List<MovieModel> {
    return this.map { it.toModel() }
}