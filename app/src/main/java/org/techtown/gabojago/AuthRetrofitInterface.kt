package org.techtown.gabojago

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/auth/naver")
    fun login(@Body params: HashMap<String, Any>): Call<AuthResponse>
}