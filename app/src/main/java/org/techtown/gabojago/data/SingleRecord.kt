package org.techtown.gabojago.data

data class SingleRecord(
    var id: Int = 0,
    var title: String? = "",
    var resultType: String? = "",
    var coverImg: Int? = null,
    var isSelected: Boolean? = null,
    var typeImg: Int? = null
)
