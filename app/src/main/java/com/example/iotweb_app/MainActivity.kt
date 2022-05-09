package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity() {
    val server_url = "tcp://192.168.0.24:1883"//broker의 ip와 port
    var mymqtt : MyMqtt? = null
    val sub_topic = "iot/#"
    var Room: RoomLightFrag?=null
    var CCTV: cctv?=null
    var Curtain: curtain?=null
    var Alarm: alarm?=null
    var Pet: pet?=null
    var Temp: temp?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Mqtt통신을 수행할 Mqtt객체를 생성
        mymqtt = MyMqtt(this,server_url)
        //브로커에서 메시지 전달 시 호출될 메소드를 넘기기
        mymqtt?.mysetCallback(::onReceived)
        //브로커 연결
        mymqtt?.connect(arrayOf<String>(sub_topic))
        //이벤트 연결하기
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

    fun onReceived(topic:String,message: MqttMessage){
        //토픽의 수신을 처리
        //EditText에 내용을 출력하기, 영상출력, 도착된 메시지 안에서 온도랑 습도 데이터를 이용해서 차트그리기,
        //모션 감지시지 알림 발생 등

        val msg = String(message.payload)
        var list1 = msg.split(":")
        var topicin = topic.split("/")
        if(topicin[1] == "tem"){

        }else if (topicin[1] == "pir"){

        }
        Log.d("mqtt",msg)
    }
}