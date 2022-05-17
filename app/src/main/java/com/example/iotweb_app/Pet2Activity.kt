package com.example.iotweb_app

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.pet.*
import kotlinx.android.synthetic.main.pet_timesettings.*

class Pet2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_timesettings)
        val intent = intent

        settings_save.setOnClickListener {

            intent.putExtra("firstAlarm", "${petalarm1.hour}:${petalarm1.minute}:0")
            intent.putExtra("secondAlarm", "${petalarm2.hour}:${petalarm2.minute}:0")

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}