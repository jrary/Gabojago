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
//data class RecordFolderMakeResponse(
//    @SerializedName("isSuccess") val isSuccess: Boolean,
//    @SerializedName("code") val code: Int,
//    @SerializedName("message") val message: String
//)


data class RandomResultListResult(
    @SerializedName("randomResultIdx") val randomResultIdx: Int,
    @SerializedName("randomResultType") val randomResultType: String,
    @SerializedName("randomResultContent") val randomResultContent: String,
    @SerializedName("createAt") val createAt: String
)

data class SingleResultListResult(
    @SerializedName("hasRecording") val hasRecording: Boolean,
    @SerializedName("randomResultListResult") val randomResultListResult: ArrayList<RandomResultListResult>,
)

data class SingleResultListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SingleResultListResult
)

data class RecordCountResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Int
)