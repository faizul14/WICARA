package com.CapstoneProject.wicara.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("article")
    fun getArtikel() : Call<ArtikelResponse>

    @GET("youtube")
    fun getVidio() : Call<VidioResponse>


    @GET("users")
    fun getArtikelEx() : Call<TestResponse>
}