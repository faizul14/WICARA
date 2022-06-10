package com.CapstoneProject.wicara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.CapstoneProject.wicara.databinding.ActivityAutentikasiLoginBinding

class AutentikasiLoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAutentikasiLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutentikasiLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}