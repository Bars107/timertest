package com.bars.timer_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bars.timer_test.presentation.view.TimerFragment
import com.bars.timer_test.presentation.view.TimerFragment.Companion.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.parent, TimerFragment(), TAG)
            .commit()
    }
}