package com.example.examen.utils

import androidx.annotation.Keep
import com.example.examen.pojos.UserRequest
import retrofit2.Call
import retrofit2.http.GET

@Keep
interface UsersServices {

    @GET("?results=10")
    fun getUsers(): Call<UserRequest>
}