package org.techtown.gabojago.menu.record.recordRetrofit

import retrofit2.Call
import retrofit2.http.*

interface RecordRetrofitInterface {
    @GET("/app/recordingList/each/{date}")
    fun getSingleResultList(
        @Header("x-access-token") xAccessToken: String,
        @Path("date") date: String
    ): Call<SingleResultListResponse>

    @POST("/app/folder/new")
    fun putFolderMakeIdx(
        @Header("x-access-token") xAccessToken: String,
        @Body randomResultIdx : randomResultRequest
    ): Call<RecordFolderMakeResponse>


    @GET("/app/randomresultcount/{date}")
    fun getDateCount(
        @Header("x-access-token") xAccessToken: String,
        @Path("date") date: String
    ): Call<RecordCountResponse>

    @GET("/app/recordingList/folder/{date}")
    fun getFolderResultList(
        @Header("x-access-token") xAccessToken: String,
        @Path("date") date: String
    ): Call<FolderResultListResponse>
}