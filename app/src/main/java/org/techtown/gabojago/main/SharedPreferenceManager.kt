package org.techtown.gabojago

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
fun getRetrofit(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.121.202")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}
