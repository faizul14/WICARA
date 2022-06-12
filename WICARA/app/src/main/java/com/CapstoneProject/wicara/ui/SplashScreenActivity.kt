package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.CapstoneProject.wicara.AutentikasiLoginActivity
import com.CapstoneProject.wicara.MainActivity
import com.CapstoneProject.wicara.MainActivity2
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivitySplashScreenBinding
import com.CapstoneProject.wicara.sharedPreferences.UserModel
import com.CapstoneProject.wicara.sharedPreferences.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenActivity : AppCompatActivity() {
    private var binding : ActivitySplashScreenBinding? = null
    private lateinit var mUserPreference: UserPreferences
    private lateinit var userModel: UserModel
    private var session = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        hideSystemUI()

        //for get session
        mUserPreference = UserPreferences(this)
        userModel = mUserPreference.getUser()

        session = userModel.sessionGuide

        move()
    }

    private fun move(){
        lifecycleScope.launch(Dispatchers.Default){
            delay(1500)
            withContext(Dispatchers.Main){
                if (session){
                    val move = Intent(this@SplashScreenActivity, MainActivity2::class.java)
                    startActivity(move)
                    finish()
                }else{
                    val move = Intent(this@SplashScreenActivity, GuideActivity::class.java)
                    startActivity(move)
                    finish()
                }


            }
        }
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