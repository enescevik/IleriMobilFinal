package com.halic.ilerimobilfinal.apis

import com.halic.ilerimobilfinal.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("DisneyPosters.json")
    suspend fun getMovies(): Response<List<MovieModel>>
}