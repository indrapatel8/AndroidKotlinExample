package com.indra.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Welcome : AppCompatActivity() {
    private lateinit var txtUserName: TextView
    private lateinit var btnLogout: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        txtUserName = findViewById(R.id.txtUserName)
        btnLogout = findViewById(R.id.btnLogout)
        txtUserName.text = "Welcome : "+auth.currentUser?.email
        btnLogout.setOnClickListener({
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
    }
}