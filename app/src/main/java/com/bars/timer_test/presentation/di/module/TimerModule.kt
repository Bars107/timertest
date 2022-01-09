package com.bars.timer_test.presentation.di.module

import com.bars.timer_test.data.TimerRepository
import com.bars.timer_test.data.source.ITimerDataStore
import com.bars.timer_test.data.source.TimerLocalDataStore
import com.bars.timer_test.domain.interactor.ITimerUseCase
import com.bars.timer_test.domain.interactor.TimerUseCase
import com.bars.timer_test.domain.repository.ITimerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class TimerModule {

    @Binds
    abstract fun getTimeUseCase(useCase: TimerUseCase): ITimerUseCase

    @Binds
    abstract fun getTimerRepository(repo: TimerRepository): ITimerRepository

    @Binds
    abstract fun getTimerDataStore(dataStore: TimerLocalDataStore): ITimerDataStore
}