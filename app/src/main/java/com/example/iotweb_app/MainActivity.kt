package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var Room: RoomLightFrag?=null
    var CCTV: cctv?=null
    var Curtain: curtain?=null
    var Alarm: alarm?=null
    var Pet: pet?=null
    var Temp: temp?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Room = RoomLightFrag()
        CCTV = cctv()
        Curtain = curtain()
        Alarm = alarm()
        Pet = pet()
        Temp = temp()
        val myadapter = ArrayAdapter.createFromResource(this,R.array.mysensor_data,android.R.layout.simple_spinner_item)
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        iot_spin.adapter = myadapter

        iot_spin.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when(position){
                    0 -> changefrag("room_light")
                    1 -> changefrag("CCTV")
                    2 -> changefrag("Curtain")
                    3 -> changefrag("Alarm")
                    4 -> changefrag("pet")
                    5 -> changefrag("temp")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
    fun changefrag(name:String?){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when(name){
            "room_light" ->{
                transaction.replace(R.id.container,Room!!)
            }
            "CCTV" ->{
                transaction.replace(R.id.container,CCTV!!)
            }
            "Curtain" ->{
                transaction.replace(R.id.container,Curtain!!)
            }
            "Alarm" ->{
                transaction.replace(R.id.container,Alarm!!)
            }
            "pet" ->{
                transaction.replace(R.id.container,Pet!!)
            }
            "temp" ->{
                transaction.replace(R.id.container,Temp!!)
            }
        }
        transaction.commit()
    }
}