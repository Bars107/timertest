package com.bars.timer_test.data.source

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import javax.inject.Inject

class TimerLocalDataStore @Inject constructor(): ITimerDataStore {

    override suspend fun loadTimer(context: Context): Long {
        val pref = context.getSharedPreferences(timer_preference_key, MODE_PRIVATE)
        return pref.getString(timer_value_key, "0")!!.toLong()

    }

    override fun saveTimer(timer: Long, context: Context) {
        val pref = context.getSharedPreferences(timer_preference_key, MODE_PRIVATE)
        with(pref.edit()) {
            putString(timer_value_key, timer.toString())
            //using commit instead of apply since in this case we need
            // to ensure data saved exactly at this moment
            commit()
        }
        Log.d(tag, "Data saved")
    }

    companion object {
        val timer_value_key = "timer_value_pref_key"
        val timer_preference_key = "timer_shared_preference_key"
        val tag = TimerLocalDataStore::class.java.simpleName
    }
}