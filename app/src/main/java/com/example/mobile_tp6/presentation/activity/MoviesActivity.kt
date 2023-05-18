package com.example.mobile_tp6.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mobile_tp6.R
import com.example.mobile_tp6.presentation.adapter.MovieAdapter
import com.example.mobile_tp6.data.database.MoviesRoomDatabase
import com.example.mobile_tp6.databinding.ActivityMoviesBinding
import com.example.mobile_tp6.presentation.mvvm.contract.MainContract
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.util.dialogs.ErrorDialogFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MainContract.ViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Test error dialog
        binding.failure.setOnClickListener {
            ErrorDialogFragment.newInstance(
                getString(R.string.title_dialog),
                getString(R.string.description_dialog)
            ).show(supportFragmentManager, getString(R.string.error_dialog))
        }

        binding.buttonBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val db: MoviesRoomDatabase by lazy {
            Room
                .databaseBuilder(this, MoviesRoomDatabase::class.java, "Movie-DB")
                .build()
        }

        viewModel.callService()
        viewModel.getValueViewModel().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()) {
                    binding.recycler.isVisible = false
                    binding.errorEmptyState.isVisible = true
                    binding.failure.isVisible = true
                } else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MainViewModel.MainStatus.ERROR -> ErrorDialogFragment.newInstance(
                getString(R.string.title_dialog),
                getString(R.string.description_dialog)
            ).show(supportFragmentManager, getString(R.string.error_dialog))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}