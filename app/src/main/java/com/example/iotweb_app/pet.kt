package com.example.iotweb_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.pet.*

class pet:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pet,container,false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var alarmhour1 = petalarm1.hour
        var alarmmin1 = petalarm1.minute
        var alarmhour2 = petalarm2.hour
        var alarmmin2 = petalarm2.minute
        Log.d("time","${alarmhour1},${alarmmin1}")
        Log.d("time","${alarmhour2},${alarmmin2}")
    }
}