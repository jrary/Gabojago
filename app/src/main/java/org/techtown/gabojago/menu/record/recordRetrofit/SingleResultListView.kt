package org.techtown.gabojago.menu.record.recordRetrofit

interface SingleResultListView {
    fun onSingleResultList(result: Int)
    fun onSingleResultList(code: Int, message: String)
}