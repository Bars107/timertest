package com.bars.timer_test.data.source

import android.content.Context

interface ITimerDataStore {
    suspend fun loadTimer(context: Context): UInt
    fun saveTimer(timer: UInt, context: Context)
}