package com.example.test_lab_week_12.repository

import android.util.Log
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.database.MovieDatabase
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {

    private val apiKey = com.example.test_lab_week_12.BuildConfig.TMDB_API_KEY

    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            val movieDao = movieDatabase.movieDao()
            val savedMovies = movieDao.getMovies()
            if (savedMovies.isEmpty()) {
                val movies = movieService.getPopularMovies(apiKey).results
                movieDao.addMovies(movies)
                emit(movies)
            } else {
                emit(savedMovies)
            }
        }.flowOn(Dispatchers.IO)
    }

    // Fungsi baru untuk refresh data dari network
    suspend fun fetchMoviesFromNetwork() {
        val movieDao = movieDatabase.movieDao()
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            val moviesFetched = popularMovies.results
            movieDao.addMovies(moviesFetched)
        } catch (e: Exception) {
            Log.d("MovieRepository", "An error occurred: ${e.message}")
        }
    }
}
