package com.CapstoneProject.wicara.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityTextToTextBinding
import com.CapstoneProject.wicara.viewmodel.TextToTextViewModel

class TextToTextActivity : AppCompatActivity(), View.OnClickListener {
    private var binding : ActivityTextToTextBinding? = null
    private lateinit var viewModel : TextToTextViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextToTextBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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
                val move = Intent(this, ChoseLanguageActivity::class.java)
                move.putExtra(ChoseLanguageActivity.DATA, languagae)
                startActivity(move)
            }
        }
    }
}