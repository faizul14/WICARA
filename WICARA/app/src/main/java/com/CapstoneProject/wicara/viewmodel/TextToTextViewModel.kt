package com.CapstoneProject.wicara.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.launch

class TextToTextViewModel : ViewModel() {
    private val _resultText =  MutableLiveData<String>()
    val resultText : LiveData<String> = _resultText

    fun setTextResultEx(data : String){
        _resultText.value = data
    }
    fun setTextResultEx2(word : String, dataset: String, context: Context){
        viewModelScope.launch {
            if (!Python.isStarted()) {
                Python.start(AndroidPlatform(context))
            }
            val python = Python.getInstance()
            val pyObject = python.getModule("TranslateTextToText")
            var hasil = ""
            try {
                hasil =  pyObject.callAttr("translate_bahasa", word.toString().toLowerCase(), dataset.toString()).toString()
            }catch (e : Exception){
                Log.d("TRANSLATE", e.toString())
                hasil = "Dalam penambahan data bahasa !"
            }
            Log.d("hasil", hasil.toString())
            _resultText.value = hasil.toString()
        }

    }

    private val _codeLeft = MutableLiveData<String>()
    val codeLeft : LiveData<String> = _codeLeft

    private val _codeRight = MutableLiveData<String>()
    val codeRight : LiveData<String> = _codeRight


    fun generateCode(code: String , location: String){
        if (location.equals("left")) _codeLeft.value = code else _codeRight.value = code

    }
}