package org.techtown.gabojago.menu.record

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import org.techtown.gabojago.menu.record.recordRetrofit.RecordCountView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecordService {
    private lateinit var recordCountView: RecordCountView
    fun setRecordCountView(recordCountView: RecordCountView){
        this.recordCountView = recordCountView
    }
//    private lateinit var singleResultListView: SingleResultListView
//
//
//    fun setsingleResultListView(singleResultListView: SingleResultListView) {
//        this.randomResultView = randomResultView
//    }
//
//    fun setRecordFolderMakeView(recordFolderMakeView: RecordFolderMakeView) {
//        this.recordFolderMakeView = recordFolderMakeView
//    }
//
//    fun getRandomResultList(userJwt: String, date: Int) {
//        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
//        recordService.getRandomResultList(userJwt, date).enqueue(object :
//            Callback<RecordListResponse> {
//            override fun onResponse(
//                call: Call<RecordListResponse>,
//                response: Response<RecordListResponse>
//            ) {
//                Log.d("RECORDRESULT/Response", response.toString())
//                val resp = response.body()!!
//                Log.d("RECORDRESULT/Code", resp.code.toString())
//
//                if (resp.isSuccess) {
//                    randomResultView.onRandomResultSuccess(resp.result!!)
//                } else {
//                    when (resp.code) {
//                        2012 -> randomResultView.onRandomResultFailure(resp.code, "회원 정보가 잘못되었습니다.")
//                        2000 -> randomResultView.onRandomResultFailure(resp.code, resp.message)
//                        3000 -> randomResultView.onRandomResultFailure(resp.code, resp.message)
//                        5008 -> randomResultView.onRandomResultFailure(resp.code, resp.message)
//                        2013 -> randomResultView.onRandomResultFailure(resp.code, resp.message)
//                        5016 -> randomResultView.onRandomResultFailure(resp.code, resp.message)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<RecordListResponse>, t: Throwable) {
//                randomResultView.onRandomResultFailure(400, t.toString())
//                Log.d("CALENDARGETADV", t.toString())
//            }
//        })
//    }
//
//    fun putFolderMakeIdx(userJwt: String, randomResultIdx : ArrayList<Int>) {
//        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
//        recordService.putFolderMakeIdx(userJwt, randomResultIdx).enqueue(object :
//            Callback<RecordFolderMakeResponse> {
//            override fun onResponse(
//                call: Call<RecordFolderMakeResponse>,
//                response: Response<RecordFolderMakeResponse>
//            ) {
//                Log.d("RECORDRESULT/Response", response.toString())
//                val resp = response.body()!!
//                Log.d("RECORDRESULT/Code", resp.code.toString())
//
//                if (resp.isSuccess) {
//                    recordFolderMakeView.onRecordFolderMakeSuccess()
//                } else {
//                    when (resp.code) {
//                        2012 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, "회원 정보가 잘못되었습니다.")
//                        2000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
//                        3000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
//                        5008 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
//                        2013 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
//                        5016 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<RecordFolderMakeResponse>, t: Throwable) {
//                recordFolderMakeView.onRecordFolderMakeFailure(400, t.toString())
//                Log.d("CALENDARGETADV", t.toString())
//            }
//        })
//    }
//}

    fun recordCount(userJwt: String,date:String) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.getDateCount(userJwt, date).enqueue(object :
            Callback<RecordCountResponse> {
            override fun onResponse( call: Call<RecordCountResponse>,
                response: Response<RecordCountResponse>
            ) {
                Log.d("RECORDCOUNT/Response", response.toString())
                val resp = response.body()!!
                Log.d("RECORDCOUNT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    recordCountView.onRecordCountSuccess(resp.result)
                } else {
                    when (resp.code) {
                        2012 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                        2000 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                        3000 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                        5007 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                        2013 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                        5016 -> recordCountView.onRecordCountFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<RecordCountResponse>, t: Throwable) {
                recordCountView.onRecordCountFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }
}