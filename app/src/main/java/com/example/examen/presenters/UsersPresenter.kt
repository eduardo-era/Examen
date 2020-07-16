package com.example.examen.presenters

import android.content.Context
import com.example.examen.interfaces.Users
import com.example.examen.models.UsersModel
import com.example.examen.pojos.UserRequest
import com.example.examen.utils.GeneralUtilities
import com.example.examen.views.UsersActivity

class UsersPresenter(val view: UsersActivity):Users.Presenter {

    private val model = UsersModel(this)

    override fun getUsers(context:Context) {
        if (GeneralUtilities.isNetworkAvaliable(context)){
            model.getUsers()
        }else{
            view.noConnectionData()
        }
    }

    override fun usersObtained(users: UserRequest) {
        val usersObtained = users.users
        view.usersObtained(usersObtained!!)
    }

    override fun tryAgain() {
        view.tyAgain()
    }
}