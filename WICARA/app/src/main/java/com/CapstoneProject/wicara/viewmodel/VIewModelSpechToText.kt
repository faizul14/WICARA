package com.CapstoneProject.wicara.viewmodel

import android.os.Environment
import android.util.Log
import androidx.browser.trusted.Token
import androidx.browser.trusted.Token.create
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.CapstoneProject.wicara.network.ApiConfig
import com.CapstoneProject.wicara.network.ResponseTest2
import com.CapstoneProject.wicara.network.TranslateResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class VIewModelSpechToText : ViewModel() {

    var audio = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Aku.wav"
    private val _translate = MutableLiveData<TranslateResponse>()
    val translate : LiveData<TranslateResponse> = _translate


    var file: File = File(audio)
//    var audioBody: RequestBody = create(MediaType.parse("audio/*"), path)
//    var audioBody: RequestBody = create(MediaType.parse("audio/*") + path.toString())
//    var requestBody: Token? = create("audio/mp3*".toMediaTypeOrNull().toString(), audio)

    val audioMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
        "file",
        file.name,
        audio.toRequestBody()
    )

    fun getTranslate(){
        val client = ApiConfig.getApiServices().translate(audioMultipart)
        client!!.enqueue(object : Callback<TranslateResponse>{
            override fun onResponse(
                call: Call<TranslateResponse>,
                response: Response<TranslateResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    _translate.value = responseBody.keyword as TranslateResponse
                }else{
                    Log.d(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }

        })
    }

    fun getTest(){
        val jsonObject = JSONObject()
        jsonObject.put("name", "Jack")
        jsonObject.put("salary", "3540")
        jsonObject.put("age", "23")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val client = ApiConfig.getApiServices().getTest(requestBody)
        client.enqueue(object : Callback<ResponseTest2>{
            override fun onResponse(call: Call<ResponseTest2>, response: Response<ResponseTest2>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    Log.e("RESPONSETEST", responseBody.test.toString())
                }else{
                    Log.e("RESPONSETEST", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ResponseTest2>, t: Throwable) {
                Log.e("RESPONSETEST", t.message.toString())
            }

        })

    }

    companion object{
        const val TAG = "VIewModelSpechToText"
    }
}