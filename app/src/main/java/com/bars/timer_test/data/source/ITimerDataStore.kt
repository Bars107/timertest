package com.bars.timer_test.data.source

import android.content.Context

interface ITimerDataStore {
    suspend fun loadTimer(context: Context): Long
    fun saveTimer(timer: Long, context: Context)
}