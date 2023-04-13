package com.indra.firebaseapplicationconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("Employee")

        //this is for writing node/value to firebase
      //  myRef.child("3").child("id123").setValue("20032009")
        //myRef.child("3").child("name123").setValue("Mr. def")
      //  Log.i("ISP-1","SUCCESS-ISP-1")


        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        Log.w("ISP-1",
                            "Record No : "+it.toString());
                        Log.w("FB-DATA",it.child("id").getValue().toString()+
                                " and Name is "+it.child("name").getValue().toString())
                    }
                /*
                while (snapshot.children){

                     }

                 */
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ISP-1", "Failed to read value.", error.toException())
            }
        })



    }
}