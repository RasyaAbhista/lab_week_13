package com.example.test_lab_week_12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_lab_week_12.databinding.ActivityMainBinding
import com.example.test_lab_week_12.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(object : MovieAdapter.MovieClickListener {
        override fun onMovieClick(movie: com.example.test_lab_week_12.model.Movie) {

            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_TITLE, movie.title)
            intent.putExtra(DetailsActivity.EXTRA_RELEASE, movie.releaseDate)
            intent.putExtra(DetailsActivity.EXTRA_OVERVIEW, movie.overview)
            intent.putExtra(DetailsActivity.EXTRA_POSTER, movie.posterPath)

            startActivity(intent)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pakai DataBindingUtil
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Siapkan adapter
        binding.movieList.adapter = movieAdapter

        // Buat ViewModel dengan repo dari MovieApplication
        val movieRepository = (application as MovieApplication).movieRepository
        val movieViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(movieRepository) as T
                }
            }
        )[MovieViewModel::class.java]

        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this   // HARUS ADA untuk StateFlow binding
    }
}
