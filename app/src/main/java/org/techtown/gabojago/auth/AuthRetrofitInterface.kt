package org.techtown.gabojago.auth

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthRetrofitInterface {
    @GET("/auth/naver")
    fun login(
        @Query("access_token") access_token: String
    ): Call<AuthResponse>
}