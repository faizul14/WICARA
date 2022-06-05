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
        client.enqueue(object : Callback<ArtikelResponse>{
            override fun onResponse(
                call: Call<ArtikelResponse>,
                response: Response<ArtikelResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    _dataArtikel.value = responseBody.artikelResponse as List<ArtikelResponseItem>
                }else{
                    Log.e("RESPONSEAPI", response.message())
                }
            }

            override fun onFailure(call: Call<ArtikelResponse>, t: Throwable) {
                Log.e("RESPONSEAPI", t.message.toString())
            }

        })
    }

    private val _dataArtikel1 = MutableLiveData<List<DataItem>>()
    val dataArtikel1 : LiveData<List<DataItem>> = _dataArtikel1

    fun getDataArtikel1(){
        val client = ApiConfig.getApiServices().getArtikelEx()
        client.enqueue(object : Callback<TestResponse>{
            override fun onResponse(
                call: Call<TestResponse>,
                response: Response<TestResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    _dataArtikel1.value = responseBody.data as List<DataItem>
                }else{
                    Log.e("RESPONSEAPI", response.message())
                }
            }

            override fun onFailure(call: Call<TestResponse>, t: Throwable) {
                Log.e("RESPONSEAPI", t.message.toString())
            }

        })
    }

}