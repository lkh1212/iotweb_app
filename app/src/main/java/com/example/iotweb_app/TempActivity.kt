package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.temp.*
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.io.*

class TempActivity : AppCompatActivity() {
    val sub_topic = "android/dht"
    val server_uri = "tcp://192.168.50.201:1883"
    var mymqtt: MyMqtt? = null
    var data = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temp)
        mymqtt = MyMqtt(this, server_uri)
        mymqtt?.connect(arrayOf<String>(sub_topic))
        mymqtt?.mysetCallback(::onReceived)
        val file = File(filesDir.absolutePath+"/dht.txt")
        if(file.exists()) {
            data = readTextFile(filesDir.absolutePath + "/dht.txt").split(",")
            receivehumid.text = data[1]+"%"
            receivetemp.text = data[2]+"도"
            receivedust.text = data[3].replace("]","")
        }else{
            writeTextFile(filesDir.absolutePath,"/dht.txt","temp")
        }

    }
    fun onReceived(topic:String, message: MqttMessage) {
        val msg = String(message.payload).split(":")
        receivehumid.text = msg[1]+"%"
        receivetemp.text = msg[2]+"도"
        receivedust.text = msg[3]
        writeTextFile(filesDir.absolutePath,"/dht.txt",msg.toString())
    }
    fun readTextFile(fullpath:String): String {
        val file = File(fullpath)

        if(!file.exists())
            return "안됨미"

        val reader = FileReader(file)
        val buffer = BufferedReader(reader)
        var temp:String? = ""
        val result = StringBuffer()

        while(true) {
            temp = buffer.readLine()
            if(temp == null)
                break
            else result.append(temp)
        }
        buffer.close()
        return result.toString()
    }
    fun writeTextFile(directory:String, filename: String, content:String) {
        val dir = File(directory)
        if(!dir.exists()) //해당 경로에 파일이 있는지 확인
            dir.mkdirs() // 없다면 디렉터리 생성

        val writer = FileWriter(directory+filename) // 경로+이름으로 Filewriter객체 생성
        val buffer = BufferedWriter(writer)

        buffer.write(content)
        buffer.close()
    }
}