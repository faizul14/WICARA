package com.CapstoneProject.wicara.sharedPreferences

import android.content.Context

internal class UserPreferences(context: Context) {
    companion object{
        private const val PREF_NAME = "pref_name"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PHONE_NUMBER = "phone_number"
        private const val SESSION_GUIDE = "session"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun setUser(data : UserModel){
        val editor = preferences.edit()
        editor.putString(NAME, data.name)
        editor.putString(EMAIL, data.email)
        editor.putString(PHONE_NUMBER, data.phoneNUmber)
        editor.putBoolean(SESSION_GUIDE, data.sessionGuide)
        editor.apply()
    }

    fun getUser() : UserModel{
        val model = UserModel()
        model.name = preferences.getString(NAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.phoneNUmber = preferences.getString(PHONE_NUMBER, "")
        model.sessionGuide = preferences.getBoolean(SESSION_GUIDE, false)

        return model
    }
}