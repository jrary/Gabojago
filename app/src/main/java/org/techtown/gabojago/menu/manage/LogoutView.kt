package org.techtown.gabojago.menu.manage

interface LogoutView {
    fun onLogoutLoading()
    fun onLogoutSuccess()
    fun onLogoutFailure(code: Int, message: String)
}