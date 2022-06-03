package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivitySpechToTextBinding

class SpechToTextActivity : AppCompatActivity() {
    private var binding : ActivitySpechToTextBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpechToTextBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}