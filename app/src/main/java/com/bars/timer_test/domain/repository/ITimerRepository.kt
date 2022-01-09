package com.bars.timer_test.domain.repository

import android.content.Context

interface ITimerRepository {
    suspend fun loadTimer(context: Context): UInt
    fun saveTimer(timer: UInt, context: Context)
}