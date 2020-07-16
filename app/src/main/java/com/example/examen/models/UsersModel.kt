package com.example.examen.models

import android.util.Log
import com.example.examen.interfaces.Users
import com.example.examen.pojos.UserRequest
import com.example.examen.presenters.UsersPresenter
import com.example.examen.utils.Application
import com.example.examen.utils.RequestRetrofit
import com.example.examen.utils.UsersServices
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class UsersModel(val presenter: UsersPresenter):Users.Model {

    private val pokemonServices: UsersServices = RequestRetrofit.url(Application.getInstance()).create(UsersServices::class.java)

    override fun getUsers() {
        val callUsers = pokemonServices.getUsers()
        callUsers.enqueue(object : retrofit2.Callback<UserRequest>{
            override fun onFailure(call: Call<UserRequest>, t: Throwable) {
                Log.e("Servicio", "EL SERVICIO FALLO")
                presenter.tryAgain()
            }

            override fun onResponse(call: Call<UserRequest>, response: Response<UserRequest>) {
                if (response.isSuccessful){
                    presenter.usersObtained(response.body()!!)
                    Log.e("Servicio", Gson().toJson(response.body()))
                }
            }
        })
    }
}