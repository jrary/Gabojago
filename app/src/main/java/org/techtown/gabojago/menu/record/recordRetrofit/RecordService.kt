package org.techtown.gabojago.menu.record.recordRetrofit

import android.util.Log
import org.techtown.gabojago.main.getRetrofit
import org.techtown.gabojago.menu.record.RecordFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class RecordService {
    private lateinit var recordCountView: RecordCountView

    fun setRecordCountView(recordCountView: RecordCountView) {
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

    private lateinit var folderRecordingView: FolderRecordingView

    fun setFolderRecordingView(folderRecordingView: FolderRecordingView) {
        this.folderRecordingView = folderRecordingView
    }

    private lateinit var folderLookView: FolderLookView

    fun setFolderLookView(folderLookView: FolderLookView) {
        this.folderLookView = folderLookView
    }

    private lateinit var folderDeleteView: FolderDeleteView

    fun setFolderDeleteView(folderDeleteView: FolderDeleteView) {
        this.folderDeleteView = folderDeleteView
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
                        6012 -> singleResultListView.onSingleResultListFailure(resp.code,
                            "회원 정보가 잘못되었습니다.")
                        5008 -> singleResultListView.onSingleResultListFailure(resp.code,
                            resp.message)
                        2013 -> singleResultListView.onSingleResultListFailure(resp.code,
                            resp.message)
                        5002 -> singleResultListView.onSingleResultListFailure(resp.code,
                            resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<SingleResultListResponse>, t: Throwable) {
                singleResultListView.onSingleResultListFailure(400, t.toString())
                Log.d("CHECK", t.toString())
            }
        })
    }

    fun putFolderMakeIdx(userJwt: String, randomResultIdx: List<Int>) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.putFolderMakeIdx(userJwt, randomResultRequest(randomResultIdx))
            .enqueue(object :
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
                            2013 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                "회원 정보가 잘못되었습니다.")
                            7000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                resp.message)
                            7003 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                resp.message)
                            7001 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                resp.message)
                            4000 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                resp.message)
                            6012 -> recordFolderMakeView.onRecordFolderMakeFailure(resp.code,
                                resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<RecordFolderMakeResponse>, t: Throwable) {
                    recordFolderMakeView.onRecordFolderMakeFailure(400, t.toString())
                    Log.d("CALENDARGETADV", t.toString())
                }
            })
    }

    fun recordCount(userJwt: String) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordCountView.onRecordCountLoading()

        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        recordService.recordCount(userJwt, stringDate).enqueue(object :
            Callback<RecordCountResponse> {

            override fun onResponse(
                call: Call<RecordCountResponse>,
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

    fun getFolderResultList(userJwt: String, date: String) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.getFolderResultList(userJwt, date).enqueue(object :
            Callback<FolderResultListResponse> {
            override fun onResponse(
                call: Call<FolderResultListResponse>,
                response: Response<FolderResultListResponse>
            ) {
                Log.d("FOLDERRESULT/Response", response.toString())
                val resp = response.body()!!
                Log.d("FOLDERRESULT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    folderResultListView.onFolderResultListSuccess(resp.result)
                } else {
                    when (resp.code) {
                        6012 -> folderResultListView.onFolderResultListFailure(resp.code,
                            resp.message)
                        5008 -> folderResultListView.onFolderResultListFailure(resp.code,
                            resp.message)
                        2013 -> folderResultListView.onFolderResultListFailure(resp.code,
                            resp.message)
                        5017 -> folderResultListView.onFolderResultListFailure(resp.code,
                            resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<FolderResultListResponse>, t: Throwable) {
                recordCountView.onRecordCountFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }

    fun putFolderRecord(userJwt: String, folderIdx: Int, folderRecording: FolderRecordingRequest) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.putFolderRecord(userJwt, folderIdx, folderRecording)
            .enqueue(object :
                Callback<FolderRecordResponse> {
                override fun onResponse(
                    call: Call<FolderRecordResponse>,
                    response: Response<FolderRecordResponse>
                ) {
                    Log.d("RECORDRESULT/Response", response.toString())
                    val resp = response.body()!!
                    Log.d("RECORDRESULT/Code", resp.code.toString())

                    if (resp.isSuccess) {
                        folderRecordingView.onFolderRecordingSuccess()
                    } else {
                        when (resp.code) {
                            2012 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                "회원 정보가 잘못되었습니다.")
                            2013 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            2000 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            3000 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            6000 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            5005 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            5006 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            6006 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            5009 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            7006 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            5011 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            6016 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            6017 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            6005 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                            4000 -> folderRecordingView.onFolderRecordingFailure(resp.code,
                                resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<FolderRecordResponse>, t: Throwable) {
                    recordCountView.onRecordCountFailure(400, t.toString())
                    Log.d("CALENDARGETADV", t.toString())
                }
            })
    }

    fun getFolderLook(userJwt: String, folderIdx: Int) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.getFolderLook(userJwt, folderIdx).enqueue(object :
            Callback<FolderLookResponse> {
            override fun onResponse(
                call: Call<FolderLookResponse>,
                response: Response<FolderLookResponse>
            ) {
                Log.d("FOLDERRESULT/Response", response.toString())
                val resp = response.body()!!
                Log.d("FOLDERRESULT/Code", resp.code.toString())

                if (resp.isSuccess) {
                    folderLookView.onFolderLookSuccess(resp.result)
                } else {
                    when (resp.code) {
                        6012 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        6000 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        2013 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        7006 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        7004 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        6013 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        6021 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        6001 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                        4000 -> folderLookView.onFolderLookFailure(resp.code,
                            resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<FolderLookResponse>, t: Throwable) {
                folderLookView.onFolderLookFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }

    fun putIdx(userJwt: String, resultIdx:List<Int>, folderIdx: List<Int>) {
        val recordService = getRetrofit().create(RecordRetrofitInterface::class.java)
        recordService.putIdx(userJwt, FolderDeleteRequest(resultIdx,folderIdx)).enqueue(object :
            Callback<FolderDeleteResponse> {
            override fun onResponse(
                call: Call<FolderDeleteResponse>,
                response: Response<FolderDeleteResponse>
            ) {
                Log.d("FOLDERDELETE/Response", response.toString())
                val resp = response.body()!!
                Log.d("FOLDERDELETE/Code", resp.code.toString())

                if (resp.isSuccess) {
                    folderDeleteView.onFolderDeleteSuccess()
                } else {
                    when (resp.code) {
                        6012 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        6000 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        2013 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        7006 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        7004 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        6013 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        6021 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        6001 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                        4000 -> folderDeleteView.onFolderDeleteFailure(resp.code,
                            resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<FolderDeleteResponse>, t: Throwable) {
                folderDeleteView.onFolderDeleteFailure(400, t.toString())
                Log.d("CALENDARGETADV", t.toString())
            }
        })
    }
}
