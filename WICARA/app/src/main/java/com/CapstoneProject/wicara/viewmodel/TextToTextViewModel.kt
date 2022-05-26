package com.CapstoneProject.wicara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextToTextViewModel : ViewModel() {
    private val _resultText =  MutableLiveData<String>()
    val resultText : LiveData<String> = _resultText

    fun setTextResultEx(data : String){
        _resultText.value = data
    }
}