package org.techtown.gabojago.menu.record.calender

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import org.techtown.gabojago.main.getjwt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarService {

    private lateinit var nicknameAdventureView: NicknameAdventureView


    fun setNicknameAdventureView(nicknameAdventureView: NicknameAdventureView){
        this.nicknameAdventureView = nicknameAdventureView
    }

    fun getNicknameAdventure() {
        val manageService = getRetrofit().create(CalendarRetrofitInterface::class.java)
        manageService.getNicknameAdventure().enqueue(object : Callback<NicknameAdventureResponse> {
            override fun onResponse(call: Call<NicknameAdventureResponse>, response: Response<NicknameAdventureResponse>) {
                Log.d("NICKNAMEACT/Response", response.toString())
                val resp = response.body()!!
                Log.d("NICKNAMEACT/Code", resp.code.toString())

                if(resp.isSuccess){
                        nicknameAdventureView.onNicknameSuccess(resp.result.userNickname)
                    }
                else {
                        nicknameAdventureView.onNicknameFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<NicknameAdventureResponse>, t: Throwable) {
                nicknameAdventureView.onNicknameFailure(400, "Network Error")
            }
        })
    }
}