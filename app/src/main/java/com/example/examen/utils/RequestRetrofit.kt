package com.example.examen.utils

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestRetrofit {
    companion object{
        fun url(context: Context): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}