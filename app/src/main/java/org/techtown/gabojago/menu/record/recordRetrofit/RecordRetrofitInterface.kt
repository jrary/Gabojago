package org.techtown.gabojago.menu.record

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RecordRetrofitInterface {
    @GET("/app/recordingList/each/{date}")
    fun getSingleResultList(
        @Header("x-access-token") xAccessToken: String,
        @Path("date") date: Int
    ): Call<SingleResultListResponse>
//
//    @GET("/app/folder/new")
//    fun putFolderMakeIdx(
//        @Header("x-access-token") xAccessToken: String,
//        @Body randomResultIdx : ArrayList<Int>
//    ): Call<RecordFolderMakeResponse>
//}

    @GET("/app/randomresultcount/{date}")
    fun getDateCount(
        @Header("x-access-token") xAccessToken: String,
        @Path("date") date: String
    ): Call<RecordCountResponse>
}