package com.abhiram.agrocare.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepo @Inject constructor(
    private val appContext : Application
) {
    var sharedPreferences : SharedPreferences
    val USERNAME = "username"
    val ISFIRST = "isfirst"
    val UID = "uid"

    init {
        sharedPreferences = appContext.getSharedPreferences("my_app_prefs",Context.MODE_PRIVATE)
    }

    fun setUname(uname :String){
        sharedPreferences.edit().putString(USERNAME,uname).apply()
    }

    fun getUname() : String{
        return sharedPreferences.getString(USERNAME,"").toString()
    }

    fun setUID(uid : String){
        sharedPreferences.edit().putString(UID,uid).apply()
    }

    fun getUserID() : String{
        return sharedPreferences.getString(UID,"").toString()
    }

    fun getIsFirst() : Boolean {
        return sharedPreferences.getBoolean(ISFIRST,true)
    }

    fun setNotFirst(){
        sharedPreferences.edit().putBoolean(ISFIRST,false).apply()
    }

}