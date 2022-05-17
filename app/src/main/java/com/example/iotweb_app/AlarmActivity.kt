package com.example.iotweb_app

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.alarm.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AlarmActivity : AppCompatActivity() {

    var dateString = ""
    var timeString = ""
    var Re_date = ""
    var Re_time = ""

    private var alarmManager: AlarmManager? = null
    private var mCalender: GregorianCalendar? = null
    private var notificationManager: NotificationManager? = null
    var builder: NotificationCompat.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        mCalender = GregorianCalendar()
        Log.d("AlarmActivity", mCalender!!.time.toString())
        setContentView(R.layout.alarm)

        val cal = Calendar.getInstance()

        date.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                Re_date = "${year}-${month+1}-${dayOfMonth}"
                result.text = "날짜/시간 : "+dateString + " / " + timeString
            }
            DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        time.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}시 ${minute}분"
                Re_time = "${hourOfDay}:${minute}:00"
                result.text = "날짜/시간 : "+ dateString + " / " + timeString
            }
            TimePickerDialog(this,timeSetListener, cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }

        //접수일 알람 버튼
        save.setOnClickListener {
            Toast.makeText(applicationContext,"${timeString}으로 알람이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            //AlarmReceiver에 값 전달
            val receiverIntent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0)
            val from = "${Re_date} ${Re_time}" //임의로 날짜와 시간을 지정

            //날짜 포맷을 바꿔주는 소스코드
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:00")
            var datetime: Date? = null
            try {
                datetime = dateFormat.parse(from)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val calendar = Calendar.getInstance()
            calendar.time = datetime
            alarmManager!![AlarmManager.RTC, calendar.timeInMillis] = pendingIntent
        }
    }
}