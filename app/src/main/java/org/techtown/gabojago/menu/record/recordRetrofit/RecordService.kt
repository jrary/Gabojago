package org.techtown.gabojago.menu.record.recordRetrofit

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import org.techtown.gabojago.menu.record.RecordFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecordService {
    private lateinit var recordCountView: RecordCountView

    fun setRecordCountView(recordCountView: RecordFragment){
        this.recordCountView = recordCountView
    }
    private lateinit var singleResultListView: SingleResultListView

    fun setSingleResultListView(singleResultListView: SingleResultListView) {
        this.singleResultListView = singleResultListView
    }

    private lateinit var recordFolderMakeView: RecordFolderMakeView

    fun setRecordFolderMakeView(recordFolderMakeView: RecordFolderMakeView) {
        this.recordFolderMakeView = recordFolderMakeView
    }

    private lateinit var folderResultListView: FolderResultListView

    fun setFolderResultListView(folderResultListView: FolderResultListView) {
        this.folderResultListView = folderResultListView
    }

    fun getSingleResultList(userJwt: String, date: String) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.getSingleResultList(userJwt, date).enqueue(object :
            Callback<SingleResultListResponse> {
            override fun onResponse(
                call: Call<SingleResultListResponse>,
                response: Response<SingleResultListResponse>
            ) {
                Log.d("SINGLERESULT/Response", response.toString())
                val resp = response.body()!!
                Log.d("SINGLERESULT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    singleResultListView.onSingleResultListSuccess(resp.result!!)
                } else {
                    when (resp.code) {
                        6012 -> singleResultListView.onSingleResultListFailure(resp.code, "회원 정보가 잘못되었습니다.")
                        5008 -> singleResultListView.onSingleResultListFailure(resp.code, resp.message)
                        2013 -> singleResultListView.onSingleResultListFailure(resp.code, resp.message)
                        5002 -> singleResultListView.onSingleResultListFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<SingleResultListResponse>, t: Throwable) {
                singleResultListView.onSingleResultListFailure(400, t.toString())
                Log.d("CHECK", t.toString())
            }
        })
    }

    fun putFolderMakeIdx(userJwt: String, randomResultIdx : List<Int>) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.putFolderMakeIdx(userJwt, randomResultRequest(randomResultIdx)).enqueue(object :
            Callback<RecordFolderMakeResponse> {
            override fun onResponse(
                call: Call<RecordFolderMakeResponse>,
                response: Response<RecordFolderMakeResponse>
            ) {
                Log.d("RECORDRESULT/Response", response.toString())
                val resp = response.body()!!
                Log.d("RECORDRESULT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    recordFolderMakeView.onRecordFolderMakeSuccess()
                } else {
                    when (resp.code) {
                        2013 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, "회원 정보가 잘못되었습니다.")
                        7000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
                        7003 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
                        7001 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
                        4000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
                        6012 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<RecordFolderMakeResponse>, t: Throwable) {
                recordFolderMakeView.onRecordFolderMakeFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }


    fun getDateCount(userJwt: String,date:String) {
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

    fun getFolderResultList(userJwt: String,date:String) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.getFolderResultList(userJwt, date).enqueue(object :
            Callback<FolderResultListResponse> {
            override fun onResponse( call: Call<FolderResultListResponse>,
                                     response: Response<FolderResultListResponse>
            ) {
                Log.d("FOLDERRESULT/Response", response.toString())
                val resp = response.body()!!
                Log.d("FOLDERRESULT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    folderResultListView.onFolderResultListSuccess(resp.result)
                } else {
                    when (resp.code) {
                        6012 -> folderResultListView.onFolderResultListFailure(resp.code, resp.message)
                        5008 -> folderResultListView.onFolderResultListFailure(resp.code, resp.message)
                        2013 -> folderResultListView.onFolderResultListFailure(resp.code, resp.message)
                        5017 -> folderResultListView.onFolderResultListFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<FolderResultListResponse>, t: Throwable) {
                recordCountView.onRecordCountFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }
}