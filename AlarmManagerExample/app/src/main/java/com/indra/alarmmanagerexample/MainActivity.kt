package com.indra.alarmmanagerexample
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.indra.alarmmanagerexample.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnSelectTime.setOnClickListener {
                showTimePicker()
            }
            btnSetAlarm.setOnClickListener {
                setAlarm()
            }
            btnCancelAlarm.setOnClickListener {
                cancelAlarm()
            }
        }
        createNotificationChannel()
    }


    private fun showTimePicker() {
        timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alram Time")
            .build()
        timePicker.show(supportFragmentManager, "TimePickerTag")
        timePicker.addOnPositiveButtonClickListener {
            if (timePicker.hour > 12) {
                binding.txtTime.text =
                    String.format("%02d", timePicker.hour - 12) + " : " + String.format(
                        "%02d",
                        timePicker.minute
                    ) + " PM"
            } else {
                binding.txtTime.text =
                    String.format("%02d", timePicker.hour) + " : " + String.format(
                        "%02d",
                        timePicker.minute
                    ) + " AM"
            }
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = timePicker.hour
            calendar[Calendar.MINUTE] = timePicker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(this, "Alarm Set Succussfully", Toast.LENGTH_LONG).show()
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this, "Alarm Cancel", Toast.LENGTH_LONG).show()
    }

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(
                "com.indra.alarmmanagerexample",
                "ChannelName",
                NotificationManager.IMPORTANCE_HIGH
            )
        channel.description = "Channel Description"
        val notificationManager = getSystemService(
            NotificationManager::class.java
        )
        notificationManager.createNotificationChannel(channel)
    }
}