package com.CapstoneProject.wicara.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityTextToTextBinding
import com.CapstoneProject.wicara.viewmodel.TextToTextViewModel
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class TextToTextActivity : AppCompatActivity(), View.OnClickListener {
    private var binding : ActivityTextToTextBinding? = null
    private lateinit var viewModel : TextToTextViewModel
    private  var codeLeft : String = "2"
    private  var codeRight : String = "1"

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == ChoseLanguageActivity.RESULT_CODE && result.data != null){
            val selectedLanguage = result.data?.getStringExtra(ChoseLanguageActivity.EXTRA_SELECTED_LANGUAGE)
            val location = result.data?.getStringExtra(ChoseLanguageActivity.LOCATION_RESULT)
            val code = result.data?.getStringExtra(ChoseLanguageActivity.CODE_DATASET_RESULT)
            valueResult(selectedLanguage.toString(), location.toString())
            viewModel.generateCode(code.toString(), location. toString())
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

        viewModel.codeLeft.observe(this, {result ->
            if (result != null){
                codeLeft = result
            }
        })

        viewModel.codeRight.observe(this, {result ->
            if (result != null){
                codeRight = result
            }
        })


        binding?.btnCleartext?.setOnClickListener(this)
        binding?.btnTerjemah?.setOnClickListener(this)
        binding?.txtBahasa1?.setOnClickListener(this)
        binding?.txtBahasa2?.setOnClickListener(this)
        binding?.imgReplaceBahasas?.setOnClickListener(this)

    }

    private fun python(word: String , dataset: String){
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val python = Python.getInstance()
        val pyObject = python.getModule("TranslateTextToText")
        val hasil =  pyObject.callAttr("translate_bahasa", word.toString(), dataset.toString())
        Log.d("hasil", hasil.toString())
        Toast.makeText(this, hasil.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_cleartext ->{
                binding?.edtTranslate?.text?.clear()
                viewModel.setTextResultEx("Hasil Translate")
            }
            R.id.btn_terjemah -> {
                var text = binding?.edtTranslate?.text.toString()

                if (text.isEmpty()){
                    binding?.edtTranslate?.error = "isi ini dulu"
                }else{
//                    viewModel.setTextResultEx(text)
                    viewModel.setTextResultEx2(text, "Dataset_$codeLeft$codeRight", context = this)
                }
            }
            //for still test
            R.id.txt_bahasa1 -> {
                val languagae = binding?.txtBahasa1?.text.toString()
                if (!languagae.equals("Indonesia")){
                    move(languagae, "left")
                }
            }
            R.id.txt_bahasa2 ->{
                val language = binding?.txtBahasa2?.text.toString()
                if (!language.equals("Indonesia")){
                    move(language, "right")
                }
            }
            R.id.img_replace_bahasas ->{
                replaceLocatin()
            }
        }
    }

    private fun valueResult(data: String, location: String){
        if (location.equals("left")) {
            binding?.txtBahasa1?.text = data

        }else {
            binding?.txtBahasa2?.text = data
        }

    }

    private fun move(data: String, location: String){
        val move = Intent(this, ChoseLanguageActivity::class.java)
        move.putExtra(ChoseLanguageActivity.DATA, data)
        move.putExtra(ChoseLanguageActivity.LOCATION, location)
        resultLauncher.launch(move)
    }

    private fun replaceLocatin(){
        var helperLanguage = binding?.txtBahasa1?.text.toString()
        var helperCode = codeLeft
        var helperText = binding?.edtTranslate?.text
        val helperHasil = binding?.txtHasilTranslate?.text

        binding?.txtBahasa1?.text = binding?.txtBahasa2?.text.toString()
        viewModel.generateCode(codeRight, "left")
        viewModel.codeLeft.observe(this, {
            codeLeft = it
        })

        binding?.txtBahasa2?.text = helperLanguage
        viewModel.generateCode(helperCode, "right")
        viewModel.codeRight.observe(this, {
            codeRight = it
        })

        if (helperText!!.isNotEmpty() && helperHasil != getString(R.string.value)){
            val data = binding?.txtHasilTranslate?.text.toString()
            binding?.edtTranslate?.setText(data)
            binding?.txtHasilTranslate?.text = helperText
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
}