package com.example.examen.pojos

import com.google.gson.annotations.SerializedName

class Location {
    @SerializedName("street")
    var street: Street? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("country")
    var country: String? = null

    @SerializedName("coordinates")
    var coordinates: Coordinates? = null

    @SerializedName("postcode")
    var postcode: Int? = null
}