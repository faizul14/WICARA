package com.CapstoneProject.wicara.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {
    private var binding : ActivityGuideBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        hideSystemUI()
    }

    //for hide top bar
    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}