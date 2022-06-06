package com.CapstoneProject.wicara.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("article")
    fun getArtikel() : Call<List<ArtikelResponseItem>>

    @GET("youtube")
    fun getVidio() : Call<List<VidioResponseItem>>


    @GET("users")
    fun getArtikelEx() : Call<TestResponse>
}