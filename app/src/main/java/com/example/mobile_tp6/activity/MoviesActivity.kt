package com.example.mobile_tp6.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mobile_tp6.adapter.MovieAdapter
import com.example.mobile_tp6.database.MovieDatabaseImplementation
import com.example.mobile_tp6.database.MoviesRoomDatabase
import com.example.mobile_tp6.databinding.ActivityMainBinding
import com.example.mobile_tp6.databinding.ActivityMoviesBinding
import com.example.mobile_tp6.mvvm.contract.MainContract
import com.example.mobile_tp6.mvvm.model.MainModel
import com.example.mobile_tp6.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.mvvm.viewmodel.factory.ViewModelFactory
import com.example.mobile_tp6.service.MovieClient
import com.example.mobile_tp6.service.MovieRequestGenerator
import com.example.mobile_tp6.service.MovieServiceImplementation
import com.example.mobile_tp6.util.ErrorDialogFragment

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var viewModel: MainContract.ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)
        with(binding.buttonBackToMain) {
            setOnClickListener {
                startActivity(intent)
                finish()
            }
        }

        val db: MoviesRoomDatabase by lazy {
            Room
                .databaseBuilder(this, MoviesRoomDatabase::class.java, "Movie-DB")
                .build()
        }
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MainModel(
                        MovieServiceImplementation(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDatabaseImplementation(db.movieDao())
                    ),
                ),
            ),
        )[MainViewModel::class.java]

        viewModel.getValueViewModel().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()) {
                    binding.recycler.isVisible = false
                    binding.failure.isVisible = true
                } else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MainViewModel.MainStatus.ERROR -> ErrorDialogFragment()
        }
    }
    fun showDialog(){
        val dialog = ErrorDialogFragment()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}