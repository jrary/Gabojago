package org.techtown.gabojago.menu.manage

import retrofit2.Call
import retrofit2.http.*

interface ManageRetrofitInterface {
    @GET("app/user/nickname")
    fun getNickname(
        @Header("x-access-token") xAccessToken: ManageRequest
    ): Call<NicknameResponse>

    @PATCH("/auth/user/newNickname")
    fun modifyNickname(
        @Header("x-access-token") xAccessToken: String,
        @Body newNickname: String,
    ): Call<NewNicknameResponse>
}