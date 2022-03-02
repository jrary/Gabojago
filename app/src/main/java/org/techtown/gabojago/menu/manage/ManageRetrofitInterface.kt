package org.techtown.gabojago.menu.manage

import retrofit2.Call
import retrofit2.http.*

interface ManageRetrofitInterface {
    @GET("/app/user/nickname")
    fun getNickname(
        @Header("x-access-token") xAccessToken: String
    ): Call<NicknameResponse>

    @PATCH("/app/user/newNickname")
    fun modifyNickname(
        @Header("x-access-token") xAccessToken: String,
        @Body newNickname: NewNickName,
    ): Call<CheckUserResponse>

    @GET("/app/user/logout")
    fun logout(
        @Header("x-access-token") xAccessToken: String
    ): Call<CheckUserResponse>

    @POST("/app/user/withdrawal")
    fun withdrawal(
        @Header("x-access-token") xAccessToken: String
    ): Call<CheckUserResponse>
}