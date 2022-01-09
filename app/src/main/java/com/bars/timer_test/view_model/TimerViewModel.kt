package com.bars.timer_test.view_model

import androidx.lifecycle.ViewModel
import com.bars.timer_test.domain.interactor.TimerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerUseCase: TimerUseCase): ViewModel() {

}