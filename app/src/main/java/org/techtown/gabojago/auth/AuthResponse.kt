package org.techtown.gabojago.auth

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("type") val type: String,
    @SerializedName("jwt") val jwt: String)

data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)