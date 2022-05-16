package com.example.iotweb_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.pet.*
import org.eclipse.paho.client.mqttv3.MqttMessage

class PetActivity : AppCompatActivity(), View.OnClickListener {
    val server_uri = "tcp:// :1883"
    var mymqtt : MyMqtt?=null
    val sub_topic = "mypet/waterlevel"
    val sub_topic2 = "mypet/setTime"

    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet)
        mymqtt = MyMqtt(this, server_uri)
        mymqtt?.mysetCallback(::onReceived)
        mymqtt?.connect(arrayOf<String>(sub_topic, sub_topic2))
        //mymqtt?.connect(arrayOf<String>(sub_topic2))

        pet_btn1.setOnClickListener (this)
        pet_btn2.setOnClickListener(this)
        feedtime_settings.setOnClickListener(this)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                val alarm1 = result.data?.getStringExtra("firstAlarm")
                val alarm2 = result.data?.getStringExtra("secondAlarm")
                mymqtt?.publish("mypet/setTimeA", "$alarm1/$alarm2")
                val myalarm1 = alarm1?.split(":")
                val myalarm2 = alarm2?.split(":")
                feed_time.text = "아침 "+ myalarm1?.get(0)+":"+ myalarm1?.get(1)+"\n"+"점심 "+ myalarm2?.get(0)+":"+ myalarm2?.get(1)
            }
        }
    }

    override fun onClick(v: View?) {
        var data:String=""
        if(v?.id == R.id.pet_btn1){
            data = "mypetfeed"
            mymqtt?.publish("mypet/feed", data)
        }else if(v?.id == R.id.pet_btn2){
            data = "mypetwater"
            mymqtt?.publish("mypet/water", data)
        }else if(v?.id == R.id.feedtime_settings){
            val intent = Intent(this, Pet2Activity::class.java).apply{
                putExtra("time1", "time1_ok")
                putExtra("time2", "time2_ok")
            }
            resultLauncher.launch(intent)
        }
    }
    fun onReceived(topic:String, message: MqttMessage){
        val msg = String(message.payload)
        val topic = topic
        if (topic == "mypet/waterlevel"){
            waterlevel.text = "$msg cm"
        }else if (topic == "mypet/setTime"){
            val alarmset = msg.split("/")
            val alarm1 = alarmset[0]
            val alarm2 = alarmset[1]
            feed_time.text = "아침 "+alarm1+"\n"+"점심 "+alarm2
        }
    }
}