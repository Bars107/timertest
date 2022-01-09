package com.bars.timer_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bars.timer_test.R
import com.bars.timer_test.databinding.FragmentTimerBinding
import com.bars.timer_test.view_model.TimerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private val timerViewModel: TimerViewModel by viewModels()

    private var _binding: FragmentTimerBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var isTicking: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()

        timerViewModel.loadLastTimerValue(requireContext())

        binding.startStopBtn.setOnClickListener {
            startStopTimer()
        }
    }

    private fun startStopTimer() {
        isTicking = if (isTicking) {
            timerViewModel.stopTimer()
            binding.startStopBtn.setText(R.string.start)
            false
        } else {
            timerViewModel.startTimer()
            binding.startStopBtn.setText(R.string.stop)
            true
        }
    }

    private fun subscribeToEvents(){
        timerViewModel.loadingViewState.observe(viewLifecycleOwner, {
            onLoadingFinished(it)
        })
        timerViewModel.dataViewState.observe(viewLifecycleOwner, {
            onDataChanged(it)
        })
        timerViewModel.tickingViewState.observe(viewLifecycleOwner, {
            onDataChanged(it)
        })
    }

    private fun onLoadingFinished(status: Boolean){
        binding.run {
            progress.isVisible = false
            startStopBtn.isVisible = true
            timerTv.isVisible = true
        }

        if (!status) {
            Toast.makeText(requireContext(), R.string.loading_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun onDataChanged(value: UInt) {
        binding.timerTv.text = convertTimeToString(value)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun convertTimeToString(value: UInt) : String{
        //TODO implement proper convertion
        return value.toString()
    }

    companion object {
        val TAG = TimerFragment::class.java.simpleName
    }
}