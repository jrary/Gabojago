package org.techtown.gabojago.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.ApplicationClass.Companion.X_ACCESS_TOKEN
import org.techtown.gabojago.ApplicationClass.Companion.mSharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun saveJwt(jwtToken: String) {
    val editor = mSharedPreferences.edit()
    editor.putString(X_ACCESS_TOKEN, jwtToken)

    editor.apply()
}

fun getJwt(): String? = mSharedPreferences.getString(X_ACCESS_TOKEN, null)

