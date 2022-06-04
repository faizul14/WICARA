package com.CapstoneProject.wicara.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class VidioResponse(

	@field:SerializedName("VidioResponse")
	val vidioResponse: List<VidioResponseItem?>? = null
)

@Parcelize
data class VidioResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("link_youtube")
	val linkYoutube: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable
