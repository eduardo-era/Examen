package com.example.examen.pojos

import com.google.gson.annotations.SerializedName

class Coordinates {
    @SerializedName("latitude")
    var latitude: String? = null

    @SerializedName("longitude")
    var longitude: String? = null
}