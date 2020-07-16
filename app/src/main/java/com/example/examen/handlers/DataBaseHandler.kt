package com.example.examen.handlers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.example.examen.pojos.UserList
import com.google.gson.Gson
import kotlin.coroutines.coroutineContext

class DataBaseHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable = ("CREATE TABLE $CUSTOMER_TABLE_NAME (" +
                "${COLUMN_USERID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USER_NAME TEXT," +
                "$COLUMN_USER_TEL TEXT," +
                "$COLUMN_USER_CEL TEXT," +
                "$COLUMN_USER_NAME_MAIL TEXT," +
                "$COLUMN_USER_COUNTRY TEXT," +
                "$COLUMN_USER_STATE TEXT," +
                "$COLUMN_USER_CITY TEXT," +
                "$COLUMN_USER_LANG TEXT," +
                "$COLUMN_USER_LAT TEXT)")

        db?.execSQL(createUserTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addCustomer(users: UserList){

        Log.e("DATOS PARA AGREGAR", Gson().toJson(users))
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, String.format(users.name!!.first + " " + users.name!!.first + " " + users.name!!.last ))
        values.put(COLUMN_USER_TEL, users.phone)
        values.put(COLUMN_USER_CEL, users.cell)
        values.put(COLUMN_USER_NAME_MAIL, users.email)
        values.put(COLUMN_USER_COUNTRY, users.location!!.country)
        values.put(COLUMN_USER_STATE, users.location!!.state)
        values.put(COLUMN_USER_CITY, users.location!!.city)
        values.put(COLUMN_USER_LANG, users.location!!.coordinates!!.longitude)
        values.put(COLUMN_USER_LAT, users.location!!.coordinates!!.latitude)

        val db = this.writableDatabase
        try {
            db.insert(CUSTOMER_TABLE_NAME, null, values)
        }catch (e: Exception){
            e.printStackTrace()
        }
        db.close()
    }

    fun getCustomers(context: Context): ArrayList<UserList>{
        val qry = "SELECT * FROM $CUSTOMER_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val users = ArrayList<UserList>()

        if (cursor.count != 0){

            while (cursor.moveToNext())
            {
                val user = UserList()
                user.userid = cursor.getInt(cursor.getColumnIndex(COLUMN_USERID))
                user.fullName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME))
                user.phone = cursor.getString(cursor.getColumnIndex(COLUMN_USER_TEL))
                user.cell = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CEL))
                user.country = cursor.getString(cursor.getColumnIndex(COLUMN_USER_COUNTRY))
                user.state = cursor.getString(cursor.getColumnIndex(COLUMN_USER_STATE))
                user.city = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CITY))
                user.email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME_MAIL))
                user.latitude = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAT))
                user.longitude = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LANG))
                users.add(user)
            }
        }
        cursor.close()
        db.close()
        Log.e("DATOS EN CURSOR", Gson().toJson(users))
        return users
    }

    fun dropTable(){
        val db = this.readableDatabase
        db.execSQL("DROP TABLE IF EXISTS '$CUSTOMER_TABLE_NAME'");
    }

    companion object{
        private val DATABASE_NAME = "Mydata.db"
        private val DATABASE_VERSION = 1
        val CUSTOMER_TABLE_NAME = "Users"
        val COLUMN_USERID = "userid"
        val COLUMN_USER_NAME = "username"
        val COLUMN_USER_NAME_MAIL = "usermail"
        val COLUMN_USER_LAT =  "userlat"
        val COLUMN_USER_LANG =  "userlang"
        val COLUMN_USER_TEL = "usertel"
        val COLUMN_USER_CEL = "usercel"
        val COLUMN_USER_COUNTRY = "usercountry"
        val COLUMN_USER_CITY = "usercity"
        val COLUMN_USER_STATE = "userstate"
    }
}