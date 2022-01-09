package com.bars.timer_test.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bars.timer_test.domain.interactor.TimerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerUseCase: TimerUseCase) : ViewModel() {

    //technically we don't need three LiveData here, two is enough:
    //one for the data change and another for loading state
    //however it makes more clear from development perspective to split those two
    val loadingViewState = MutableLiveData<Boolean>()
    val dataViewState = MutableLiveData<Long>()
    val tickingViewState = MutableLiveData<Long>()

    val isTicking: Boolean
        get() {
            return tickingCoroutine != null
        }

    private var lastTick: Long = 0

    //field to save ticking coroutine. See startTimer()
    private var tickingCoroutine: Job? = null

    fun loadLastTimerValue(context: Context) {
        viewModelScope.launch {
            try {
                val timerVal = timerUseCase.loadTimer(context)
                loadingViewState.value = true
                lastTick = timerVal
                dataViewState.value = timerVal
            } catch (e: Exception) {
                //it should never fail, so it's more for extending in future
                loadingViewState.value = false
                lastTick = 0
                dataViewState.value = lastTick
                Log.e(tag, "Failed to load data")
            }
        }
    }

    fun saveTimer(context: Context) {
        timerUseCase.saveTimer(lastTick, context)
    }

    fun startTimer() {
        tickingCoroutine = viewModelScope.launch {
            while (true) {
                delay(1000)
                lastTick++
                tickingViewState.value = lastTick
            }
        }
    }

    fun stopTimer() {
        tickingCoroutine?.cancel()
        tickingCoroutine = null
    }

    companion object {
        val tag: String = TimerViewModel::class.java.simpleName
    }

}