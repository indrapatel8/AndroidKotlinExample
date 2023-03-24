package com.indra.notificationexample
//https://www.youtube.com/watch?v=Fo7WksYMlCU
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.indra.notificationexample.databinding.ActivityMainBinding

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
                notificationManager.notify(1234, builder.build())
            }
        }
    }
}