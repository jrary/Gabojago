package org.techtown.gabojago.menu.record.calender

import com.google.gson.annotations.SerializedName

data class NicknameAdventureResult(
    @SerializedName("userNickname") val userNickname: String
)

data class NicknameAdventureResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: NicknameAdventureResult
)