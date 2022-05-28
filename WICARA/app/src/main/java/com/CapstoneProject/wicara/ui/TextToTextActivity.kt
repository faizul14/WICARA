package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityTextToTextBinding
import com.CapstoneProject.wicara.viewmodel.TextToTextViewModel

class TextToTextActivity : AppCompatActivity(), View.OnClickListener {
    private var binding : ActivityTextToTextBinding? = null
    private lateinit var viewModel : TextToTextViewModel

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == ChoseLanguageActivity.RESULT_CODE && result.data != null){
            val selectedLanguage = result.data?.getStringExtra(ChoseLanguageActivity.EXTRA_SELECTED_LANGUAGE)
            val location = result.data?.getStringExtra(ChoseLanguageActivity.LOCATION_RESULT)
            valueResult(selectedLanguage.toString(), location.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextToTextBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        hideSystemUI()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TextToTextViewModel::class.java]

        binding?.edtTranslate?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // not
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty())binding?.btnCleartext?.visibility = View.VISIBLE else binding?.btnCleartext?.visibility = View.GONE
            }

            override fun afterTextChanged(p0: Editable?) {
                // not
            }

        })

        viewModel.resultText.observe(this, {data ->
            binding?.txtHasilTranslate?.text = data
        })

        binding?.btnCleartext?.setOnClickListener(this)
        binding?.btnTerjemah?.setOnClickListener(this)
        binding?.txtBahasa1?.setOnClickListener(this)
        binding?.txtBahasa2?.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_cleartext ->{
                binding?.edtTranslate?.text?.clear()
                viewModel.setTextResultEx("Hasil Terrjemahan")
            }
            R.id.btn_terjemah -> {
                var text = binding?.edtTranslate?.text.toString()

                if (text.isEmpty()){
                    binding?.edtTranslate?.error = "isi ini dulu"
                }else{
                    viewModel.setTextResultEx(text)
                }
            }
            //for still test
            R.id.txt_bahasa1 -> {
                val languagae = binding?.txtBahasa1?.text.toString()
                move(languagae, "left")
            }
            R.id.txt_bahasa2 ->{
                val language = binding?.txtBahasa2?.text.toString()
                move(language, "right")
            }
        }
    }

    private fun valueResult(data: String, location: String){
        if (location.equals("left")) binding?.txtBahasa1?.text = data else binding?.txtBahasa2?.text = data

    }

    private fun move(data: String, location: String){
        val move = Intent(this, ChoseLanguageActivity::class.java)
        move.putExtra(ChoseLanguageActivity.DATA, data)
        move.putExtra(ChoseLanguageActivity.LOCATION, location)
        resultLauncher.launch(move)
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
}