package com.bars.timer_test.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bars.timer_test.domain.interactor.TimerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerUseCase: TimerUseCase) : ViewModel() {

    //techically we don't need three LiveData here, two is enough:
    //one for the data change and another for loading state
    //however it makes more clear from development perspective to split those two
    val loadingViewState = MutableLiveData<Boolean>()
    val dataViewState = MutableLiveData<UInt>()
    val tickingViewState = MutableLiveData<UInt>()

    private var lastTick: UInt = 0u
    private var coroutine: Job? = null

    fun loadLastTimerValue(context: Context) {
        viewModelScope.launch {
            try {
                val timerVal = timerUseCase.loadTimer(context)
                loadingViewState.value = true
                lastTick = timerVal
                dataViewState.value = timerVal
            } catch (e: Exception) {
                loadingViewState.value = false
                Log.e(tag, "Failed to load data")
            }
        }
    }

    fun startTimer() {
         coroutine = viewModelScope.launch {
            while(true) {
                delay(1000)
                lastTick++
                tickingViewState.value = lastTick
            }
        }
    }

    fun stopTimer() {
        coroutine?.cancel()
        coroutine = null
    }

    companion object {
        val tag: String = TimerViewModel::class.java.simpleName
    }

}