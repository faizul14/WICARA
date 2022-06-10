package com.CapstoneProject.wicara.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityChangeProfil2Binding
import com.CapstoneProject.wicara.sharedPreferences.UserModel
import com.CapstoneProject.wicara.sharedPreferences.UserPreferences

class ChangeProfilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userMOdel : UserModel
    private lateinit var binding : ActivityChangeProfil2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfil2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        userMOdel = intent.getParcelableExtra<UserModel>("USER") as UserModel
        show()


        binding.btnChangeProfil.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun show(){
        binding.textInputEditText2.setText(userMOdel.name)
        binding.textInputEditText3.setText(userMOdel.phoneNUmber)
        binding.textInputEditText4.setText(userMOdel.email)
    }


    override fun onDestroy() {
        super.onDestroy()
    }


    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101
        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_change_profil ->{
                val name = binding.textInputEditText2.text.toString()
                val phoneNumber = binding.textInputEditText3.text.toString()
                val email = binding.textInputEditText4.text.toString()

                if (name.isEmpty()){
                    binding.textInputEditText2.error = FIELD_REQUIRED
                    return
                }
                if (email.isEmpty()){
                    binding.textInputEditText4.error = FIELD_REQUIRED
                    return
                }
                if (!isValidEmail(email)){
                    binding.textInputEditText4.error = FIELD_IS_NOT_VALID
                    return
                }
                if (phoneNumber.isEmpty()){
                    binding.textInputEditText3.error = FIELD_REQUIRED
                    return
                }
                if (!TextUtils.isDigitsOnly(phoneNumber)){
                    binding.textInputEditText3.error = FIELD_DIGIT_ONLY
                    return
                }

                saveUser(name, email, phoneNumber)
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RESULT, userMOdel)
                setResult(RESULT_CODE, resultIntent)
                finish()

            }
        }
    }

    private fun saveUser(name: String, email: String, phoneNumber: String){
        val userpreference = UserPreferences(this)
        userMOdel.name = name
        userMOdel.email = email
        userMOdel.phoneNUmber = phoneNumber

        userpreference.setUser(userMOdel)
        Toast.makeText(this, "Berhasil di Update", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}