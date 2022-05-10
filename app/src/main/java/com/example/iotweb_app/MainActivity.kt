package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.encodeToString
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity() {
    var fragmentlist = ArrayList<Fragment>()
    var name = arrayOf<String>("Room","CCTV","Curtain","Alarm","Pet","Temp")
    val server_url = "tcp://192.168.0.24:1883"//broker의 ip와 port
    var mymqtt : MyMqtt? = null
    val sub_topic = "iot/#"
    var Room=RoomLightFrag()
    var CCTV=cctv()
    var Curtain=curtain()
    var Alarm=alarm()
    var Pet=pet()
    var Temp=temp()
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
        fragmentlist.add(Room)
        fragmentlist.add(CCTV)
        fragmentlist.add(Curtain)
        fragmentlist.add(Alarm)
        fragmentlist.add(Pet)
        fragmentlist.add(Temp)
        val myadapter_pager = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return fragmentlist.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentlist[position]
            }

        }
        tab_pager_iot.adapter = myadapter_pager
        TabLayoutMediator(tabs_iot,tab_pager_iot){tab,position ->
            tab.text = name[position]
        }.attach()


    }
    fun onReceived(topic:String,message: MqttMessage){
        //토픽의 수신을 처리
        //EditText에 내용을 출력하기, 영상출력, 도착된 메시지 안에서 온도랑 습도 데이터를 이용해서 차트그리기,
        //모션 감지시지 알림 발생 등

        val msg = String(message.payload)
        var list1 = msg.split(":")
        var topicin = topic.split("/")
        if(topicin[1] == "mycamera"){

        }else if (topicin[1] == "pir"){

        }
        Log.d("mqtt",msg)
    }
}