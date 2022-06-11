package com.CapstoneProject.wicara.network

import android.content.res.TypedArray
import android.util.TypedValue
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File


interface ApiServices {
    @GET("article")
    fun getArtikel() : Call<List<ArtikelResponseItem>>

    @GET("youtube")
    fun getVidio() : Call<List<VidioResponseItem>>


    @GET("users")
    fun getArtikelEx() : Call<TestResponse>

    //for translate
    @GET("predictjawa")
    fun getTranslate() : Call<TranslateResponse>


    @Multipart
    @POST("predictjawa")
    fun translate(
//        @Part("file\"; filename=\"audio.mp3\" ") file: RequestBody?
//        @Part("file\"; filename=\"audio.mp3\" ") file: RequestBody?
//        @Field("file") file: File
        @Part file: MultipartBody.Part
    ): Call<TranslateResponse>

    @GET("testing")
    fun getTest(
        @Body requestBody: RequestBody
    ) : Call<ResponseTest2>

}