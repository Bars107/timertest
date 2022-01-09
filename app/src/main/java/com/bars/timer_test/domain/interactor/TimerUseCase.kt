package com.bars.timer_test.domain.interactor

import android.content.Context
import com.bars.timer_test.domain.repository.ITimerRepository
import javax.inject.Inject

class TimerUseCase @Inject constructor(
    private val timerRepository: ITimerRepository
) : ITimerUseCase {

    override suspend fun loadTimer(context: Context): Long {
        return timerRepository.loadTimer(context)
    }

    override fun saveTimer(timer: Long, context: Context) {
        timerRepository.saveTimer(timer, context)
    }
}