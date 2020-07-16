package com.example.examen.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class UserRequest {
    @SerializedName("results")
    var users: ArrayList<UserList>? = null
}