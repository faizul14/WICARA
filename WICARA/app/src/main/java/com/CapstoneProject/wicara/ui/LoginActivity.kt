package com.CapstoneProject.wicara.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var binding : ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        binding?.btnLogin?.setOnClickListener(this)
        binding?.txtLogin?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_login ->{
                Toast.makeText(this, "LOGIN", Toast.LENGTH_SHORT).show()
            }
            R.id.txt_login ->{
//                val move = Intent(this, SignUpActivity::class.java)
//                startActivity(move)
                Toast.makeText(this, "Ke Activity Daftar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}