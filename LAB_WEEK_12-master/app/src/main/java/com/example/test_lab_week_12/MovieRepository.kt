package com.example.test_lab_week_12.repository

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {

    // API key TMDB (v3)
    private val apiKey = "2239d619c38116ed66b2ef1f844f2fcb"

    // Fetch movies using Flow + sorting
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // Ambil data dari API
            val movies = movieService.getPopularMovies(apiKey).results

            // 🔥 FILTERING DESCENDING POPULARITY
            val sortedMovies = movies.sortedByDescending { it.popularity }

            emit(sortedMovies)
        }.flowOn(Dispatchers.IO)
    }
}
