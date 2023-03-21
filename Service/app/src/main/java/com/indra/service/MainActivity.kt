package com.indra.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indra.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnStart.setOnClickListener {
                if (btnStart.text.equals("Start")) {
                    startService(Intent(this@MainActivity, PlayerService::class.java))
                    btnStart.setText("Stop")
                } else {
                    stopService(Intent(this@MainActivity, PlayerService::class.java))
                    btnStart.setText("Start")
                }
            }
        }
    }
}