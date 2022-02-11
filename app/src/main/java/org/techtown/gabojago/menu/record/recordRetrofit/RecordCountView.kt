package org.techtown.gabojago.menu.record.recordRetrofit

interface RecordCountView {
    fun onRecordCountSuccess(result: Int)
    fun onRecordCountFailure(code: Int, message: String)
}