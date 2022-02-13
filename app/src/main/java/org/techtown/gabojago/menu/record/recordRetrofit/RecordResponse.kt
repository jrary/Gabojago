package org.techtown.gabojago.menu.record.recordRetrofit
import com.google.gson.annotations.SerializedName



data class RecordFolderMakeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)


data class RandomResultListResult(
    @SerializedName("randomResultIdx") val randomResultIdx: Int,
    @SerializedName("randomResultType") val randomResultType: String,
    @SerializedName("randomResultContent") val randomResultContent: String,
    @SerializedName("createAt") val createAt: String
)

data class SingleResultListResult(
    @SerializedName("hasRecording") val hasRecording: Boolean,
    @SerializedName("randomResultListResult") val randomResultListResult: RandomResultListResult,
)

data class SingleResultListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<SingleResultListResult>
)

data class InFolderListResult(
    @SerializedName("randomResultIdx") val resultIdx: Int,
    @SerializedName("randomResultType") val resultType: String,
    @SerializedName("randomResultContent") val resultContent: String,
    @SerializedName("createAt") val createAt: String
)

data class FolderResultList(
    @SerializedName("folderIdx") val folderIdx: Int,
    @SerializedName("hasRecording") val hasRecording: Boolean,
    @SerializedName("folderTitle") val folderTitle: String,
    @SerializedName("folderListResult") val randomResultListResult: ArrayList<InFolderListResult>,
)

data class FolderResultListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<FolderResultList>
)

data class RecordCountResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Int
)

data class randomResultRequest(
    @SerializedName("randomResultIdx") val randResultIdx:List<Int>
)