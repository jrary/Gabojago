package org.techtown.gabojago.menu.record

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

//interface RecordRetrofitInterface {
//    @GET("/app/randomresultList/:{date}")
//    fun getRandomResultList(
//        @Header("x-access-token") xAccessToken: String,
//        @Path("date") date: Int
//    ): Call<RecordListResponse>
//
//    @GET("/app/folder/new")
//    fun putFolderMakeIdx(
//        @Header("x-access-token") xAccessToken: String,
//        @Body randomResultIdx : ArrayList<Int>
//    ): Call<RecordFolderMakeResponse>
//}