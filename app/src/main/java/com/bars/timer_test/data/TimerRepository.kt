package com.bars.timer_test.data

import android.content.Context
import com.bars.timer_test.data.source.ITimerDataStore
import com.bars.timer_test.domain.repository.ITimerRepository
import javax.inject.Inject

class TimerRepository @Inject constructor(private val timerLocalDataStore: ITimerDataStore) : ITimerRepository {

    override suspend fun loadTimer(context: Context): UInt {
        return timerLocalDataStore.loadTimer(context)
    }

    override fun saveTimer(timer: UInt, context: Context) {
        timerLocalDataStore.saveTimer(timer, context)
    }
}