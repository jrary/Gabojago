package org.techtown.gabojago.menu.manage

interface WithdrawalView {
    fun onWithdrawalLoading()
    fun onWithdrawalSuccess()
    fun onWithdrawalFailure(code: Int, message: String)
}