package com.bars.timer_test.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bars.timer_test.domain.interactor.TimerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerUseCase: TimerUseCase) : ViewModel() {

    val loadingViewState = MutableLiveData<Boolean>()
    val dataViewState = MutableLiveData<UInt>()
    val tickingViewState = MutableLiveData<UInt>()

    fun loadLastTimerValue(context: Context) {
        viewModelScope.launch {
            try {
                val timerVal = timerUseCase.loadTimer(context)
                loadingViewState.value = true
                dataViewState.value = timerVal
            } catch (e: Exception) {
                loadingViewState.value = false
                Log.e(tag, "Failed to load data")
            }
        }
    }

    companion object {
        val tag: String = TimerViewModel::class.java.simpleName
    }

}