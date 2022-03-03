package org.techtown.gabojago.menu.record.recordRetrofit

interface SingleResultListView {
    fun onSingleResultListSuccess(result: SingleResult)
    fun onSingleResultListFailure(code: Int, message: String)
}