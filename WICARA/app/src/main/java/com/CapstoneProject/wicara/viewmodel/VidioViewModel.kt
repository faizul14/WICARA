package com.CapstoneProject.wicara.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.CapstoneProject.wicara.network.ApiConfig
import com.CapstoneProject.wicara.network.VidioResponse
import com.CapstoneProject.wicara.network.VidioResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VidioViewModel : ViewModel() {
    private val _dataVidio = MutableLiveData<List<VidioResponseItem>>()
    val dataVidio : LiveData<List<VidioResponseItem>> = _dataVidio

    fun getVidio(){
        val client = ApiConfig.getApiServices().getVidio()
        client.enqueue(object : Callback<List<VidioResponseItem>>{
            override fun onResponse(call: Call<List<VidioResponseItem>>, response: Response<List<VidioResponseItem>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    _dataVidio.value = responseBody as List<VidioResponseItem>
                }else{
                    Log.e("RESPONSEAPI", response.message())
                }
            }

            override fun onFailure(call: Call<List<VidioResponseItem>>, t: Throwable) {
                Log.e("RESPONSEAPI", t.message.toString())
            }

        })
    }
}