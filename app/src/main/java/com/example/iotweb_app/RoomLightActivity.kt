package com.example.iotweb_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.roomlight_fragment.*

class RoomLightActivity : AppCompatActivity() {
    val sub_topic = "iot/#"
    val server_uri = "tcp://:1883" //broker의 ip와 port
    var mymqtt: MyMqtt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roomlight_fragment)
        //Mqtt통신을 수행할 Mqtt객체를 생성
        mymqtt = MyMqtt(this, server_uri)
        //브로커연결
        mymqtt?.connect(arrayOf<String>(sub_topic))

        switch_led.setOnCheckedChangeListener { buttonView, isChecked ->
            var data: String = ""
            if (isChecked) {
                data = "led_on"
            } else {
                data = "led_off"
            }
            mymqtt?.publish("iot/led", data)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mymqtt?.disconnect()
    }
}