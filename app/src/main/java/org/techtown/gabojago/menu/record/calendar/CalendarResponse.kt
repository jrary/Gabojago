package org.techtown.gabojago.menu.record.calendar

import com.google.gson.annotations.SerializedName

data class NicknameAdventureResult(
    @SerializedName("userNickname_in_calendar") val userNicknameAdventure: String
)

data class AdventureDateResult(
    @SerializedName("가입 날짜") val date: String
)

data class AdventureMonthlyTimeResult(
    @SerializedName("총 모험 횟수") val monthlyTimes: Int
)

data class AdventureTimeResult(
    @SerializedName("userjoindate") val userJoinDate: AdventureDateResult,
    @SerializedName("monthlyAdventureTimes") val monthlyAdventureTimes: AdventureMonthlyTimeResult
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
