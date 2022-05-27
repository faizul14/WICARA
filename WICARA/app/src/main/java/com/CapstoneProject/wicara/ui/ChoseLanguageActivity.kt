package com.CapstoneProject.wicara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityChoseLanguageBinding
import com.CapstoneProject.wicara.viewmodel.ChoseLanguageViewModel

class ChoseLanguageActivity : AppCompatActivity(), View.OnClickListener {

    private var binding : ActivityChoseLanguageBinding? = null
    private lateinit var viewModel : ChoseLanguageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseLanguageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ChoseLanguageViewModel::class.java]
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
            }
            R.id.cd_batak -> {
                viewModel.setLanguage("Batak")
            }
            R.id.cd_indonesia -> {
                viewModel.setLanguage("Indonesia")
            }
            R.id.cd_jawa -> {
                viewModel.setLanguage("Jawa")
            }
            R.id.cd_melayu -> {
                viewModel.setLanguage("Melayu")
            }
            R.id.cd_sunda -> {
                viewModel.setLanguage("Sunda")
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}