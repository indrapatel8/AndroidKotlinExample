package com.indra.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
       when(intent?.action){
           Intent.ACTION_TIME_TICK->{
               Toast.makeText(context,
                   "Time is Changing........",Toast.LENGTH_LONG).show()
           }
           Intent.ACTION_AIRPLANE_MODE_CHANGED->{
               Toast.makeText(context,
                   "ACTION_AIRPLANE_MODE_CHANGED",Toast.LENGTH_LONG).show()
           }
       }
    }
}