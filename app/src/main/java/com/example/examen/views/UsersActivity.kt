package com.example.examen.views

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.R
import com.example.examen.adapters.UsersAdapter
import com.example.examen.handlers.DataBaseHandler
import com.example.examen.interfaces.Users
import com.example.examen.pojos.UserList
import com.example.examen.presenters.UsersPresenter
import com.example.examen.utils.BaseActivity
import com.google.gson.Gson


class UsersActivity:BaseActivity(), Users.View {

    private val presenter = UsersPresenter(this)
    private var recyclerUsers: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        recyclerUsers = findViewById(R.id.user_list)
        dbHandler = DataBaseHandler(this, null, null, 1)
        init()
    }

    private fun init(){
        getUsers()
    }

    private fun getUsers(){
        presenter.getUsers(this)
    }

    override fun noConnectionData(){
        dismissProgress()
        val users = dbHandler.getCustomers(this)
        val adapter = UsersAdapter(this, users)
        val rv: RecyclerView = recyclerUsers!!
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }

    override fun usersObtained(users: ArrayList<UserList>){
        dismissProgress()
        for(user in users) {
            dbHandler.addCustomer(user)
        }
        Log.e("DATOS", Gson().toJson(users))
        val adapter = UsersAdapter(this, users)
        val rv: RecyclerView = recyclerUsers!!
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }

    override fun tyAgain() {
        presenter.getUsers(this)
    }

    companion object{
        lateinit var dbHandler: DataBaseHandler
    }
}