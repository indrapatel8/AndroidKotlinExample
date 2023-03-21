package com.indra.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(myBroadcastReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
        registerReceiver(myBroadcastReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

        //sendBroadcast(Intent(this, myBroadCast::class.java))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }
}