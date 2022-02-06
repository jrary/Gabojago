package org.techtown.gabojago.menu.record.calender

import retrofit2.Call
import retrofit2.http.*

interface CalendarRetrofitInterface {
    @GET("/app/user/nicknameAdventure")
    fun getNicknameAdventure(
    ): Call<NicknameAdventureResponse>
}