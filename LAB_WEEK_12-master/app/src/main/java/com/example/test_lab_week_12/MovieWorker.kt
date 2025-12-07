package com.example.test_lab_week_12.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.test_lab_week_12.MovieApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val movieRepository = (applicationContext as MovieApplication).movieRepository
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.fetchMoviesFromNetwork()
        }
        return Result.success()
    }
}
