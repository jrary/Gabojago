package org.techtown.gabojago

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun setJwt(context: Context, name: String, jwt: String) {
    val spf = context.getSharedPreferences(name, AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()
    editor.putString("jwt", jwt)
    editor.apply()
}

fun getJwt(context: Context, name: String): String{
    val spf = context.getSharedPreferences(name, AppCompatActivity.MODE_PRIVATE)

    return spf.getString("jwt", "")!!
}