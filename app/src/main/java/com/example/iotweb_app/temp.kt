package com.example.iotweb_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.temp.*

class temp:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.temp,container,false)
        return view
    }
    fun receivedata(data:String){
        receivetemp.setText(data)
//        receivehumid.setText(data)
//        receivedust.setText(data)
    }
}