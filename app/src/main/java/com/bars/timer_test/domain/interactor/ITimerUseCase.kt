package com.bars.timer_test.domain.interactor

import android.content.Context

interface ITimerUseCase {
    suspend fun loadTimer(context: Context): Long
    fun saveTimer(timer: Long, context: Context)
}