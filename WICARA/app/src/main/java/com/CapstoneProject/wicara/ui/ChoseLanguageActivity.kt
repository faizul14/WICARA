package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityChoseLanguageBinding
import com.CapstoneProject.wicara.viewmodel.ChoseLanguageViewModel

class ChoseLanguageActivity : AppCompatActivity(), View.OnClickListener {

    private var binding : ActivityChoseLanguageBinding? = null
    private lateinit var viewModel : ChoseLanguageViewModel
    private lateinit var location : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseLanguageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideSystemUI()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ChoseLanguageViewModel::class.java]
       //fot catch use language from textt to text activity
        val data = intent.getStringExtra(DATA) as String
        location = intent.getStringExtra(LOCATION)as String
        binding?.txtChoseLanguage?.text = data
        showAlpha(data)
        viewModel.language.observe(this, {data ->
            binding?.txtChoseLanguage?.text = data
            showAlpha(data)
        })


        binding?.cdBali?.setOnClickListener(this)
        binding?.cdBatak?.setOnClickListener(this)
        binding?.cdIndonesia?.setOnClickListener(this)
        binding?.cdJawa?.setOnClickListener(this)
        binding?.cdMelayu?.setOnClickListener(this)
        binding?.cdSunda?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.cd_bali -> {
                viewModel.setLanguage("Bali")
                moveResultValue("Bali", location, "2")
            }
            R.id.cd_batak -> {
                viewModel.setLanguage("Batak")
                moveResultValue("Batak", location, "3")
            }
            R.id.cd_indonesia -> {
                viewModel.setLanguage("Indonesia")
                moveResultValue("Indonesia", location, "1")
            }
            R.id.cd_jawa -> {
                viewModel.setLanguage("Jawa")
                moveResultValue("Jawa", location, "4")
            }
            R.id.cd_melayu -> {
                viewModel.setLanguage("Melayu")
                moveResultValue("Melayu", location, "7")
            }
            R.id.cd_sunda -> {
                viewModel.setLanguage("Sunda")
                moveResultValue("Sunda", location, "8")
            }

        }
    }

    private fun showAlpha(data: String){
        when(data){
            "Bali" -> {
                binding?.imgBali?.alpha = 1f
                binding?.imgBatak?.alpha = 0f
                binding?.imgIndonesia?.alpha = 0f
                binding?.imgJawa?.alpha = 0f
                binding?.imgMelayu?.alpha = 0f
                binding?.imgSunda?.alpha = 0f
            }
            "Batak" -> {
                binding?.imgBali?.alpha = 0f
                binding?.imgBatak?.alpha = 1f
                binding?.imgIndonesia?.alpha = 0f
                binding?.imgJawa?.alpha = 0f
                binding?.imgMelayu?.alpha = 0f
                binding?.imgSunda?.alpha = 0f
            }
            "Indonesia" -> {
                binding?.imgBali?.alpha = 0f
                binding?.imgBatak?.alpha = 0f
                binding?.imgIndonesia?.alpha = 1f
                binding?.imgJawa?.alpha = 0f
                binding?.imgMelayu?.alpha = 0f
                binding?.imgSunda?.alpha = 0f
            }
            "Jawa" -> {
                binding?.imgBali?.alpha = 0f
                binding?.imgBatak?.alpha = 0f
                binding?.imgIndonesia?.alpha = 0f
                binding?.imgJawa?.alpha = 1f
                binding?.imgMelayu?.alpha = 0f
                binding?.imgSunda?.alpha = 0f
            }
            "Melayu" -> {
                binding?.imgBali?.alpha = 0f
                binding?.imgBatak?.alpha = 0f
                binding?.imgIndonesia?.alpha = 0f
                binding?.imgJawa?.alpha = 0f
                binding?.imgMelayu?.alpha = 1f
                binding?.imgSunda?.alpha = 0f
            }
            "Sunda" -> {
                binding?.imgBali?.alpha = 0f
                binding?.imgBatak?.alpha = 0f
                binding?.imgIndonesia?.alpha = 0f
                binding?.imgJawa?.alpha = 0f
                binding?.imgMelayu?.alpha = 0f
                binding?.imgSunda?.alpha = 1f
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

    private fun moveResultValue(language : String, location: String, code: String){
        val moveResult = Intent()
        moveResult.putExtra(EXTRA_SELECTED_LANGUAGE, language)
        moveResult.putExtra(LOCATION_RESULT, location)
        moveResult.putExtra(CODE_DATASET_RESULT, code)
        setResult(RESULT_CODE, moveResult)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        const val LOCATION = "location"
        const val DATA = "Data"
        const val LOCATION_RESULT = "location_result"
        const val EXTRA_SELECTED_LANGUAGE = "extra_selected_language"
        const val CODE_DATASET_RESULT = "code_dataset_result"
        const val RESULT_CODE = 110
    }
}