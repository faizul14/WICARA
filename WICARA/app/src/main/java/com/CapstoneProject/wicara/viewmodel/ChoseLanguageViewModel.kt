package com.CapstoneProject.wicara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChoseLanguageViewModel : ViewModel() {
    private val _language = MutableLiveData<String>()
    val language : LiveData<String> = _language

    fun setLanguage(data: String){
        _language.value = data
    }
}