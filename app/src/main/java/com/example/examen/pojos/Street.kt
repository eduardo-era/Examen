package com.example.examen.pojos

import com.google.gson.annotations.SerializedName

class Street {
    @SerializedName("number")
    var number: Int? = null

    @SerializedName("name")
    var name: String? = null
}