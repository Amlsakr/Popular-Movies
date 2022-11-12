package com.example.popularmovies.common.utill

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class SessionManager @Inject constructor (@ApplicationContext var context: Context){
    var sharedPreferences : SharedPreferences = context.getSharedPreferences(Constant.SHARED_Preference_NAME,
        Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun StoreLastUpdateTime(){
        val  lastCachingTime= Calendar.getInstance().time.time
        editor.putLong(Constant.LAST_UPDATE_TIME_KEY ,lastCachingTime)
        editor.commit()

    }

    fun getLastUpdateTime():Long {
        return sharedPreferences.getLong(Constant.LAST_UPDATE_TIME_KEY ,-4L)
    }

    fun availableToMakeForceUpdate () :Boolean{
        val last_caching_time = getLastUpdateTime()
        val diff = Calendar.getInstance().time.time - last_caching_time
        val diffInSeconds = diff/1000
        return diffInSeconds >= Constant.rate
    }

}