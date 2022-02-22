package org.techtown.gabojago.menu.record.recordRetrofit

interface SingleLookView {
    fun onSingleLookSuccess(result: SingleLookResult)
    fun onSingleLookFailure(code: Int, message: String)
}