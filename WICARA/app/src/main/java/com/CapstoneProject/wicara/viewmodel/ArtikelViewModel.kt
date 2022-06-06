package com.CapstoneProject.wicara.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.CapstoneProject.wicara.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtikelViewModel : ViewModel() {

    private val _dataArtikel = MutableLiveData<List<ArtikelResponseItem>>()
    val dataArtikel : LiveData<List<ArtikelResponseItem>> = _dataArtikel

    fun getDataArtikel(){
        val client = ApiConfig.getApiServices().getArtikel()
        client.enqueue(object : Callback<List<ArtikelResponseItem>>{
            override fun onResponse(
                call: Call<List<ArtikelResponseItem>>,
                response: Response<List<ArtikelResponseItem>>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    _dataArtikel.value = responseBody as List<ArtikelResponseItem>
                }else{
                    Log.e("RESPONSEAPI1", response.message())
                }
            }

            override fun onFailure(call: Call<List<ArtikelResponseItem>>, t: Throwable) {
                Log.e("RESPONSEAPI2", t.message.toString())
            }

        })
    }

}