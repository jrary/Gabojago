package org.techtown.gabojago.menu.manage

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import org.techtown.gabojago.main.withdrawalRetrofit
import org.techtown.gabojago.menu.manage.auth.LogoutView
import org.techtown.gabojago.menu.manage.auth.WithdrawalView
import org.techtown.gabojago.menu.manage.system.ManageRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageService {

    private lateinit var nicknameView: NicknameView
    private lateinit var newNicknameView: NewNicknameView
    private lateinit var logoutView: LogoutView
    private lateinit var withdrawalView: WithdrawalView

    fun setNicknameView(nicknameView: NicknameView){
        this.nicknameView = nicknameView
    }
    fun setNewNicknameView(newNicknameView: NewNicknameView){
        this.newNicknameView = newNicknameView
    }
    fun setLogoutView(logoutView: LogoutView){
        this.logoutView = logoutView
    }
    fun setWithdrawalView(withdrawalView: WithdrawalView){
        this.withdrawalView = withdrawalView
    }

    fun getNickname(userJwt: String) {
        nicknameView.onNicknameLoading()
        val manageService = getRetrofit().create(ManageRetrofitInterface::class.java)
        manageService.getNickname(userJwt).enqueue(object : Callback<NicknameResponse> {
            override fun onResponse(call: Call<NicknameResponse>, response: Response<NicknameResponse>) {
                Log.d("NICKNAMEACT/Response", response.toString())
                val resp = response.body()!!

                if(resp.isSuccess){
                        nicknameView.onNicknameSuccess(resp.result.userNickname)
                    }
                else {
                        nicknameView.onNicknameFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<NicknameResponse>, t: Throwable) {
                nicknameView.onNicknameFailure(400, t.message!!)
            }
        })
    }

    fun modifyNickname(userJwt: String, newNickname: String) {
        newNicknameView.onModifyNicknameLoading()
        val manageService = getRetrofit().create(ManageRetrofitInterface::class.java)

        manageService.modifyNickname(userJwt, NewNickName(newNickname)).enqueue(object : Callback<CheckUserResponse> {
            override fun onResponse(call: Call<CheckUserResponse>, response: Response<CheckUserResponse>) {
                Log.d("MODIFYACT/Response", response.toString())
                val resp = response.body()!!

                if (resp.isSuccess) {
                        newNicknameView.onModifyNicknameSuccess(newNickname)
                }
                else {
                        newNicknameView.onModifyNicknameFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<CheckUserResponse>, t: Throwable) {
                newNicknameView.onModifyNicknameFailure(400, t.message!!)
            }
        })
    }

    fun logout(userJwt: String) {
        val manageService = getRetrofit().create(ManageRetrofitInterface::class.java)

        manageService.logout(userJwt).enqueue(object : Callback<CheckUserResponse> {
            override fun onResponse(call: Call<CheckUserResponse>, response: Response<CheckUserResponse>) {
                Log.d("LOGOUTACT/Response", response.toString())
                val resp = response.body()!!

                if (resp.isSuccess) {
                    logoutView.onLogoutSuccess()
                }
                else {
                    logoutView.onLogoutFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<CheckUserResponse>, t: Throwable) {
                logoutView.onLogoutFailure(400, t.message!!)
            }
        })
    }

    fun withdrawal(userJwt: String) {
        val manageService = getRetrofit().create(ManageRetrofitInterface::class.java)

        manageService.withdrawal(userJwt).enqueue(object : Callback<CheckUserResponse> {
            override fun onResponse(call: Call<CheckUserResponse>, response: Response<CheckUserResponse>) {
                Log.d("WITHDRAWALACT/Response", response.toString())
                val resp = response.body()!!

                if (resp.isSuccess) {
                    withdrawalView.onWithdrawalSuccess()
                }
                else {
                    withdrawalView.onWithdrawalFailure(resp.code, resp.message)
                }
            }
            override fun onFailure(call: Call<CheckUserResponse>, t: Throwable) {
                withdrawalView.onWithdrawalFailure(400, t.message!!)
            }
        })
    }

    fun naverWithdrawal(clientId: String, clientSecret: String, userJwt: String) {
        val naverWithdrawal = withdrawalRetrofit().create(ManageRetrofitInterface::class.java)

        naverWithdrawal.naverWithdrawal("delete", clientId, clientSecret, userJwt, "NAVER").enqueue(object : Callback<NaverWithdrawalResponse> {
            override fun onResponse(call: Call<NaverWithdrawalResponse>, response: Response<NaverWithdrawalResponse>) {
                Log.d("WITHDRAWALACT/Response", response.toString())
                val resp = response.body()!!

                if (resp.result == "success") {
                  Log.d("NAVERWITHDRAWAL", "success")
                }
                else {
                    Log.d("NAVERWITHDRAWAL", "fail")
                }
            }
            override fun onFailure(call: Call<NaverWithdrawalResponse>, t: Throwable) {
                withdrawalView.onWithdrawalFailure(400, t.message!!)
            }
        })
    }
}