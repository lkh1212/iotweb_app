package com.example.iotweb_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.alarm.*
import kotlin.time.ExperimentalTime
import kotlin.time.minutes
import kotlinx.android.synthetic.main.activity_main.*

class alarm:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.alarm,container,false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var alarmhour = alarmtime.hour
        var alarmmin = alarmtime.minute
        Log.d("time","${alarmhour},${alarmmin}")
    }
}