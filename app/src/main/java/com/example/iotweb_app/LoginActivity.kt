package com.example.iotweb_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.json.JSONObject
import kotlin.concurrent.thread
import kotlin.reflect.typeOf

class LoginActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        handler = object:Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {

                super.handleMessage(msg)
                when(msg.what){
                    1 ->{
                        var intent = Intent(applicationContext,MainActivity::class.java)
                                startActivity(intent)
                                finish()
                    }
                }

            }
        }

        login.setOnClickListener {
            thread {
                var jsonobj = JSONObject()
                jsonobj.put("user_id",id.text)
                jsonobj.put("user_pwd",pwd.text)
                var url = "http://192.168.0.2:8000/login"

                var client = OkHttpClient()
                val jsondata = jsonobj.toString()
                val builder = Request.Builder()
                builder.url(url)
                builder.post(RequestBody.create(MediaType.parse("application/json"),jsondata))
                val myrequest:Request = builder.build()
                val response:Response = client.newCall(myrequest).execute()
                val result:String? = response.body()?.string()
                val loginchk= result!!.replace('"',' ').trim()
               // Log.d("http", "...${a!!.replace('"',' ').trim()}...${a!!.length}")
                var msg = Message()
                if(loginchk!! == "ok") {
                    msg.what = 1
                }else {
                    msg.what = 0
                }
                Log.d("http", "${msg.what}=====")
                handler.sendMessage(msg)


            }
        }
    }
}