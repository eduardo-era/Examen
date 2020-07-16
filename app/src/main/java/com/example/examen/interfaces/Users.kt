package com.example.examen.interfaces

import android.content.Context
import com.example.examen.pojos.UserList
import com.example.examen.pojos.UserRequest

class Users {
    interface View {
        fun usersObtained(users: ArrayList<UserList>)
        fun tyAgain()
        fun noConnectionData()
    }

    interface Presenter{
        fun getUsers(context: Context)
        fun usersObtained(users: UserRequest)
        fun tryAgain()
    }

    interface Model{
        fun getUsers()

    }
}