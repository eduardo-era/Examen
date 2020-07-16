package com.example.examen.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Name {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("first")
    var first: String? = null

    @SerializedName("last")
    var last: String? = null
}