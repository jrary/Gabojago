package org.techtown.gabojago.menu.record.calender

interface AdventureTimeView {
    fun onAdventureTimeSuccess(adventureTime: AdventureTimeResult)
    fun onAdventureTimeFailure(code: Int, message: String)
}