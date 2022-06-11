package com.CapstoneProject.wicara.network

import com.google.gson.annotations.SerializedName

data class TranslateResponse(

	@field:SerializedName("keyword")
	val keyword: String? = null
)
