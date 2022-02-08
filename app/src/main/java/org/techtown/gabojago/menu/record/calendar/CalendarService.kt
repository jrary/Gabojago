package org.techtown.gabojago.menu.record.calendar

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarService {
    private lateinit var nicknameAdventureView: NicknameAdventureView

    fun setNicknameAdventureView(nicknameAdventureView: NicknameAdventureView){
        this.nicknameAdventureView = nicknameAdventureView
    }

    fun getNicknameAdventure(userJwt: String) {
        val calendarService = getRetrofit().create(CalendarRetrofitInterface::class.java)
        calendarService.getNicknameAdventure(userJwt).enqueue(object : Callback<NicknameAdventureResponse> {
            override fun onResponse(call: Call<NicknameAdventureResponse>, response: Response<NicknameAdventureResponse>) {
                Log.d("NICKNAMEAD/Response", response.toString())
                val resp = response.body()!!
                Log.d("NICKNAMEAD/Code", resp.code.toString())

                if(resp.isSuccess){
                    nicknameAdventureView.onNicknameAdventureSuccess(resp.result!!)
                }
                else {
                    nicknameAdventureView.onNicknameAdventureFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<NicknameAdventureResponse>, t: Throwable) {
                nicknameAdventureView.onNicknameAdventureFailure(400, "Network Error")
            }
        })
    }

}