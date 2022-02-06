package org.techtown.gabojago.menu.record.calender

interface NicknameAdventureView {
    fun onNicknameAdventureSuccess(userNicknameAdventure: NicknameAdventureResult)
    fun onNicknameAdventureFailure(code: Int, message: String)
}