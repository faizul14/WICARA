package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.CapstoneProject.wicara.AutentikasiLoginActivity
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityGuideBinding
import com.CapstoneProject.wicara.sharedPreferences.UserModel
import com.CapstoneProject.wicara.sharedPreferences.UserPreferences

class GuideActivity : AppCompatActivity() {
    private var binding : ActivityGuideBinding? = null
    private lateinit var userMOdel : UserModel
    private lateinit var mUserPreference: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        hideSystemUI()
        val userpreference = UserPreferences(this)
        userMOdel = UserModel()
        binding!!.btnStart.setOnClickListener {
            userMOdel.sessionGuide = true
            userpreference.setUser(userMOdel)
            move()
        }
    }

    private fun move (){
        val move = Intent(this, AutentikasiLoginActivity::class.java)
        startActivity(move)
        finish()
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