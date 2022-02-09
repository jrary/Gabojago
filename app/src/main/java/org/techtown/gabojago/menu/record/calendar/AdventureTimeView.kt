package org.techtown.gabojago.menu.record.calendar

interface AdventureTimeView {
    fun onAdventureTimeSuccess(adventureTime: AdventureTimeResult)
    fun onAdventureTimeFailure(code: Int, message: String)
}