package org.techtown.gabojago.menu.record.calender

interface NicknameAdventureView {
    fun onNicknameSuccess(userNickname: String)
    fun onNicknameFailure(code: Int, message: String)
}