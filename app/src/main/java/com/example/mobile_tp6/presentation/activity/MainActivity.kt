package com.example.mobile_tp6.presentation.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_tp6.databinding.ActivityMainBinding
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getValue().observe(this){updateUI(it)}

        binding.buttonShowMovies.setOnClickListener{ mainViewModel.buttonPressed() }

    }

    private fun updateUI(status: MainViewModel.MainStatus) {
        when(status){
            MainViewModel.MainStatus.MAIN->{}
            MainViewModel.MainStatus.MOVIES->{ startMoviesActivity() }
        }
    }

    fun startMoviesActivity(){
        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
        finish()
    }

}