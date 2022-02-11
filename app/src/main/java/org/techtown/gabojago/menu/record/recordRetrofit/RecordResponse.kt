package org.techtown.gabojago.menu.record
import com.google.gson.annotations.SerializedName

//data class RecordResultList(
//    @SerializedName("randomresultList") val randomresultList: ArrayList<RecordResult>
//)
//
//data class RecordResult(
//    @SerializedName("randomResultIdx") val randomResultIdx: Int,
//    @SerializedName("randomResultContent") val randomResultContent: String,
//    @SerializedName("folderIdx") val folderIdx: Int,
//    @SerializedName("randomResultType") val randomResultType: String,
//    @SerializedName("creatAt") val creatAt: String,
//    @SerializedName("updateAt") val updateAt: String
//)
//
//data class RecordResponseResult(
//    @SerializedName("userjoindate") val userJoinDate: String,
//    @SerializedName("randomresultcount") val recordCount: Int,
//    @SerializedName("randomresultList") val recordList: RecordResultList
//)
//
//data class RecordListResponse(
//    @SerializedName("isSuccess") val isSuccess: Boolean,
//    @SerializedName("code") val code: Int,
//    @SerializedName("message") val message: String,
//    @SerializedName("result") val result: RecordResponseResult
//)
//
//data class RecordFolderMakeResponse(
//    @SerializedName("isSuccess") val isSuccess: Boolean,
//    @SerializedName("code") val code: Int,
//    @SerializedName("message") val message: String
//)

data class RecordCountResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Int
)