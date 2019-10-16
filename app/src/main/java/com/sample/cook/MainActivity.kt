package com.sample.cook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sample.cook.databinding.ActivityMainBinding
import com.sample.cook.viewmodels.BackgroundImageViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = ViewModelProviders.of(this@MainActivity)
                .get(BackgroundImageViewModel::class.java)
            lifecycleOwner = this@MainActivity
        }

        setContentView(binding.root)
        binding.bottomNavigation.setupWithNavController(
            Navigation.findNavController(this, R.id.nav_host_fragment)
        )
    }
}