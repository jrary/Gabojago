package org.techtown.gabojago.menu.randomPick.home

interface RandomView {
    fun onRandomResultSuccess()
    fun onRandomResultFailure(code: Int, message: String)
}