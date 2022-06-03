package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityArtikelBinding

class ArtikelActivity : AppCompatActivity() {
    private var binding: ActivityArtikelBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding =  null
    }
}