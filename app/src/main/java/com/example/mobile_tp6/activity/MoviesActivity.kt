package com.example.mobile_tp6.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mobile_tp6.adapter.MovieAdapter
import com.example.mobile_tp6.database.MovieDatabaseImplementation
import com.example.mobile_tp6.database.MoviesRoomDatabase
import com.example.mobile_tp6.databinding.ActivityMainBinding
import com.example.mobile_tp6.mvvm.contract.MainContract
import com.example.mobile_tp6.mvvm.model.MainModel
import com.example.mobile_tp6.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.mvvm.viewmodel.factory.ViewModelFactory
import com.example.mobile_tp6.service.MovieClient
import com.example.mobile_tp6.service.MovieRequestGenerator
import com.example.mobile_tp6.service.MovieServiceImplementation

class MoviesActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainContract.ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        viewModel.getValueViewModel().observe(this){updateUI(it)}
    }

    private fun updateUI(data: MainViewModel.MainData){
        when(data.status){
            MainViewModel.MainStatus.SHOW_INFO->{
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
            MainViewModel.MainStatus.HIDE_INFO->{}
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}