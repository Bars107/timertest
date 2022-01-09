package com.bars.timer_test.domain.repository

import android.content.Context

interface ITimerRepository {
    suspend fun loadTimer(context: Context): Long
    fun saveTimer(timer: Long, context: Context)
}