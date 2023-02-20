package com.indra.dialogsdemo

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var btnAlert: Button
    lateinit var alertDialog: AlertDialog
    lateinit var btnProgress: Button
    lateinit var pdialog: ProgressDialog;
    var counter: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initComponent()
        btnProgress = findViewById(R.id.btnProgress)
        btnAlert.setOnClickListener {
            alertDialog.show()
            Toast.makeText(this, "Good Morning..", Toast.LENGTH_LONG).show()
        }

        btnProgress.setOnClickListener {
            openProgressDialog()
        }
    }

    fun openProgressDialog() {
        pdialog.show()
        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pdialog.setTitle("Downloading..... " + counter + "/100%")
                counter = counter + 10;
            }
            override fun onFinish() {
                pdialog.dismiss()
            }
        }
        timer.start()
    }

    fun initComponent() {
        btnAlert = findViewById(R.id.btnAlert)
        //Define Alert Dialog
        alertDialog = AlertDialog.Builder(this)
            .setPositiveButton("Yes,Ad.And",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(
                        this,
                        "Thanks for selection Advance Android...",
                        Toast.LENGTH_LONG
                    ).show()
                    //alertDialog.dismiss()
                }
            )
            .setNegativeButton("DMM", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(this, "Thanks for selection DMM / SEO...", Toast.LENGTH_LONG).show()
            })
            .setNeutralButton("Cancel", DialogInterface.OnClickListener({ dialog, id ->
                Toast.makeText(this, "You can take your time for selection", Toast.LENGTH_LONG)
                    .show()
            }))
            .create()
        alertDialog.setTitle("Popup Title")
        alertDialog.setMessage("Are you sure to study android today?????")
        alertDialog.setCancelable(false)
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE,
            "BTN-Pos",
            DialogInterface.OnClickListener { dia, id ->
                Toast.makeText(this, "From Seperate Impl", Toast.LENGTH_LONG).show()
            })
        //alertDialog.create()

        pdialog = ProgressDialog(this);
        pdialog.setTitle("Downloading.....")
        pdialog.setMessage("It is taking longer time than usual...")
        pdialog.setCancelable(false)
        pdialog.setButton(ProgressDialog.BUTTON_NEUTRAL,
            "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "Rukk Gaya....", Toast.LENGTH_LONG).show()
                pdialog.dismiss()
            }
        )
    }
}