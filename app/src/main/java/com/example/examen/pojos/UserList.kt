package com.example.examen.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class UserList {

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("userid")
    var userid: Int? = null

    @SerializedName("fullname")
    var fullName: String? = null

    @SerializedName("name")
    var name: Name? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("cell")
    var cell: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("country")
    var country: String? = null

    @SerializedName("latitude")
    var latitude: String? = null

    @SerializedName("longitude")
    var longitude: String? = null

    @SerializedName("bitmap")
    var bitmap: String? = null

    @SerializedName("picture")
    var picture: Picture? = null
}