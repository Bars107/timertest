package com.bars.timer_test.data

import android.content.Context
import com.bars.timer_test.data.source.ITimerDataStore
import com.bars.timer_test.domain.repository.ITimerRepository
import javax.inject.Inject

//repository is 100% overkill here, but I wanted to show the proper project structure according to clean
class TimerRepository @Inject constructor(private val timerLocalDataStore: ITimerDataStore) : ITimerRepository {

    override suspend fun loadTimer(context: Context): Long {
        return timerLocalDataStore.loadTimer(context)
    }

    override fun saveTimer(timer: Long, context: Context) {
        timerLocalDataStore.saveTimer(timer, context)
    }
}