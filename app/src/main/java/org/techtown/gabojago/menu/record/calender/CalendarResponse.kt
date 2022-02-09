package org.techtown.gabojago.menu.record.calender

import com.google.gson.annotations.SerializedName

data class NicknameAdventureResult(
    @SerializedName("userNickname_in_calendar") val userNicknameAdventure: String
)

data class AdventureTimeResult(
    @SerializedName("userjoindate") val userJoinDate: String,
    @SerializedName("monthlyAdventureTimes") val monthlyAdventureTimes: String
)

data class NicknameAdventureResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: NicknameAdventureResult
)

data class AdventureTimeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: AdventureTimeResult
)
