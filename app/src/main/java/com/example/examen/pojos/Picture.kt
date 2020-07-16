package com.example.examen.pojos

import com.google.gson.annotations.SerializedName

class Picture {
    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null
}