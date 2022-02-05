package org.techtown.gabojago.menu.manage

interface NewNicknameView {
    fun onModifyNicknameSuccess(userNickname: String)
    fun onModifyNicknameFailure(code: Int, message: String)
}