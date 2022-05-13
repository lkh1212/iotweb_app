package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.encodeToString
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cctv.*
import kotlinx.android.synthetic.main.pet.*
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity() {
    var datalist = ArrayList<SimpleItem>()
    var imglist = ArrayList<Int>()
    val server_url = "tcp://192.168.0.24:1883"//broker의 ip와 port
    var mymqtt : MyMqtt? = null
    val sub_topic = "iot/#"
    var Room=SimpleItem("Room")
    var CCTV=SimpleItem("CCTV")
    var Curtain=SimpleItem("Curtain")
    var Alarm=SimpleItem("Alarm")
    var Pet=SimpleItem("Pet")
    var Temp=SimpleItem("Temp")
    private var mWebView // 웹뷰 선언
            : WebView? = null
    private var mWebSettings //웹뷰세팅
            : WebSettings? = null
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
        datalist.add(Room)
        datalist.add(CCTV)
        datalist.add(Curtain)
        datalist.add(Alarm)
        datalist.add(Pet)
        datalist.add(Temp)
        imglist.add(R.drawable.light48)
        imglist.add(R.drawable.cctv32)
        imglist.add(R.drawable.curtain48)
        imglist.add(R.drawable.alarmclock)
        imglist.add(R.drawable.dog50)
        imglist.add(R.drawable.temperature48)
        val adapter = SImpleItemAdapter(this,R.layout.simple_item,datalist,imglist)

        val manager = GridLayoutManager(this,2)
        myrecycler.layoutManager = manager
        myrecycler.adapter = adapter


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