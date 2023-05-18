package com.example.mobile_tp6.presentation.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_tp6.databinding.ActivityMainBinding
import org.koin.core.component.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonShowMovies.setOnClickListener{
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}