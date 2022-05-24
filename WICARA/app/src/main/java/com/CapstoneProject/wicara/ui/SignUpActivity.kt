package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityGuideBinding
import com.CapstoneProject.wicara.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideSystemUI()

        binding?.txtLogin?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txt_login -> {
                val move = Intent(this, LoginActivity::class.java)
                startActivity(move)
//                finish()
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