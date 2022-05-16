package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.curtain.*

class CurtainActivity : AppCompatActivity() {
    val sub_topic = "iot/#"
    val server_uri = "tcp:// :1883" //broker의 ip와 port
    var mymqtt: MyMqtt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.curtain)
        //Mqtt통신을 수행할 Mqtt객체를 생성
        mymqtt = MyMqtt(this, server_uri)
        //브로커연결
        mymqtt?.connect(arrayOf<String>(sub_topic))

        switch_curtain.setOnCheckedChangeListener { buttonView, isChecked ->
            var data: String = ""
            if (isChecked) {
                data = "servo_open"
            } else {
                data = "servo_close"
            }
            mymqtt?.publish("iot/servo", data)
        }
    }
}