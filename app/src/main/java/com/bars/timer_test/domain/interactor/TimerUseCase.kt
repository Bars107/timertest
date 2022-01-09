package com.bars.timer_test.domain.interactor

import android.content.Context
import com.bars.timer_test.domain.repository.ITimerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimerUseCase @Inject constructor(
    private val timerRepository: ITimerRepository
) : ITimerUseCase {

    //not in UI thread
    override suspend fun loadTimer(context: Context): Long = withContext(Dispatchers.IO) {
        return@withContext timerRepository.loadTimer(context)
    }

    override fun saveTimer(timer: Long, context: Context) {
        timerRepository.saveTimer(timer, context)
    }
}