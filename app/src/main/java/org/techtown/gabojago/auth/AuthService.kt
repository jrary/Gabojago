package org.techtown.gabojago.auth

import android.util.Log
import org.techtown.gabojago.getRetrofit
import org.techtown.gabojago.start.login.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun login(userToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.login(userToken).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("LOGINACT/Response", response.toString())
                val resp = response.body()!!
                Log.d("LOGINACT/Code", resp.code.toString())

                when (resp.code) {
                    1100 -> {
                        loginView.onLoginSuccess(resp.result)
                    }
                    else -> {
                        loginView.onLoginFailure(resp.code, resp.message)
                    }
                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                loginView.onLoginFailure(400, "Network Error")
            }
        })
    }
}