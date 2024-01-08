package com.halic.ilerimobilfinal.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.halic.ilerimobilfinal.apis.MovieApi
import com.halic.ilerimobilfinal.extensions.hasInternet
import com.halic.ilerimobilfinal.extensions.toEntity
import com.halic.ilerimobilfinal.extensions.toModel
import com.halic.ilerimobilfinal.model.MovieModel
import com.halic.ilerimobilfinal.persistence.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val context: Context
        get() = getApplication<Application>().applicationContext

    private val databaseName = "disney-video-db"
    private val baseUrl =
        "https://gist.githubusercontent.com/skydoves/aa3bbbf495b0fa91db8a9e89f34e4873/raw/a1a13d37027e8920412da5f00f6a89c5a3dbfb9a/"

    val moviesLiveData = MutableLiveData<List<MovieModel>>()

    fun fetchMovies() {
        uiScope.launch {
            moviesLiveData.value =
                if (context.hasInternet()) {
                    saveToStorage(getFromApi())
                } else {
                    getFromStorage()
                }
        }
    }

    private suspend fun getFromApi(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            delay(1000)

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
                .getMovies()
                .body() ?: listOf()
        }
    }

    private suspend fun getFromStorage(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            Room
                .databaseBuilder(context, MovieDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
                .movieDao()
                .getAll()
                ?.toModel() ?: listOf()
        }
    }

    private suspend fun saveToStorage(data: List<MovieModel>): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            Room
                .databaseBuilder(context, MovieDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
                .movieDao()
                .insertAll(data.toEntity())
            data
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}