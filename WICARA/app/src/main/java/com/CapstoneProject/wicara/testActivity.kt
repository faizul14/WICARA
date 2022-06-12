package com.CapstoneProject.wicara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.CapstoneProject.wicara.network.ApiConfig
import com.CapstoneProject.wicara.network.ResponseTest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class testActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//    test()
    }

//    private fun test(){
////        val client = ApiConfig.getApiServices().tset()
//
//        client.enqueue(object : Callback<ResponseTest>{
//            override fun onResponse(call: Call<ResponseTest>, response: Response<ResponseTest>) {
//                var responseBody = response.body()
//                if (response.isSuccessful && responseBody != null){
//                    Toast.makeText(this@testActivity, "ini hasil requestnya", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@testActivity, responseBody.message.toString(), Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this@testActivity, response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseTest>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
}