package com.CapstoneProject.wicara.sharedPreferences

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserModel(
    var name : String? = null,
    var email : String? = null,
    var phoneNUmber : String? = null
) : Parcelable
