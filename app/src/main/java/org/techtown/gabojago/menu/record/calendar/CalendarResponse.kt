package org.techtown.gabojago.menu.record.calendar

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NicknameAdventureResult(
    @SerializedName("userNickname_in_calendar") val userNicknameAdventure: String
)


data class AdventureTimeResult(
    @SerializedName("userjoindate") val userJoinDate: String,
    @SerializedName("monthlyAdventureTimes") val monthlyAdventureTimes: Int,
    @SerializedName("randomresultdateList") val randomresultdateList: ArrayList<CreateAt>
)

data class CreateAt(
    @SerializedName("day") val day: Int
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
