package com.CapstoneProject.wicara.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ArtikelResponse(

	@field:SerializedName("ArtikelResponse")
	val artikelResponse: List<ArtikelResponseItem?>? = null
)

@Parcelize
data class ArtikelResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable
