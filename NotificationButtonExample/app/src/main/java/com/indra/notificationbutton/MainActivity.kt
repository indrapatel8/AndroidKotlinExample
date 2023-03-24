package com.indra.notificationbutton

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.indra.notificationbutton.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val CHANNEL_ID = "com.indra.notificationexample"
    private val description = "Description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.apply {
            btnNotification.setOnClickListener {
                Toast.makeText(this@MainActivity, "OK", Toast.LENGTH_LONG).show()

                val intent = Intent(this@MainActivity, LauncherActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(
                    this@MainActivity,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

                notificationChannel = NotificationChannel(
                    CHANNEL_ID,
                    description,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)


                // adding action for activity
                val activityActionIntent1 = Intent(application, ActionButton1::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val activityActionPendingIntent1: PendingIntent =
                    PendingIntent.getActivity(application, 0, activityActionIntent1, 0)
                // adding action for activity
                val activityActionIntent2 = Intent(application, ActionButton2::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val activityActionPendingIntent2: PendingIntent =
                    PendingIntent.getActivity(application, 0, activityActionIntent2, 0)

                var action2: Notification.Action = Notification.Action.Builder(
                    R.drawable.ic_android_black_24dp,
                    "Button2",
                    activityActionPendingIntent2
                ).build()

                builder = Notification.Builder(this@MainActivity, CHANNEL_ID)
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this@MainActivity.resources,
                            R.mipmap.ic_launcher
                        )
                    )
                    .setContentIntent(pendingIntent)
                    //for adding action
                    .addAction(
                        R.drawable.ic_android_black_24dp,
                        "Button1", activityActionPendingIntent1
                    )
                    .addAction(action2)
                    .setContentTitle("Title")
                    .setContentText("Summary")
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                notificationManager.notify(1234, builder.build())
            }
        }
    }
}
