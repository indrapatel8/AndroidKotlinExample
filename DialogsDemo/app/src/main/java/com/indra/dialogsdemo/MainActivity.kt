package com.indra.dialogsdemo

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var btnAlert: Button
    lateinit var alertDialog: AlertDialog
    lateinit var btnProgress: Button
    lateinit var pdialog: ProgressDialog;
    lateinit var btnSimpleListDialog: Button
    lateinit var listAlertDialog: AlertDialog
    lateinit var loginAlertDialog: AlertDialog
    lateinit var btnLoginDialog: Button
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

        btnSimpleListDialog = findViewById(R.id.btnSimpleListDialog)
        btnSimpleListDialog.setOnClickListener {
            listAlertDialog.show()
        }
        btnLoginDialog = findViewById(R.id.btnLoginDialog)
        loginAlertDialog = AlertDialog.Builder(this).create()
        var customView = this.layoutInflater.inflate(R.layout.logindialog,null)
        var loginButton  = customView.findViewById<View>(R.id.btnLogin) as Button
        loginButton.setOnClickListener {
            Toast.makeText(this, "Haa, Custom Dialog avdi gayu", Toast.LENGTH_LONG).show()
            loginAlertDialog.dismiss()
        }
        loginAlertDialog.setView(customView)
        loginAlertDialog.setTitle("Signin")
        btnLoginDialog.setOnClickListener {
            loginAlertDialog.show()
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
                { dialog, id ->
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
            { dia, id ->
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

        createListDialog()

    }


    fun createListDialog() {
        listAlertDialog = AlertDialog.Builder(this)
            //Simple List Demo
            /* .setItems(R.array.semesterList, { dialog, which ->
                Toast.makeText(this, "Selection is : " + which, Toast.LENGTH_LONG).show()
                dialog.dismiss()
            })
              */
            //Radiobutton type list demo
            /*
        .setSingleChoiceItems(R.array.semesterList, 2,
            { dialog, which ->
                Toast.makeText(this, "Selection is : " + which, Toast.LENGTH_LONG).show()
                dialog.dismiss()
            })
*/
            .setMultiChoiceItems(R.array.semesterList, null,
                { dialog, which, chkStatus ->
                    Toast.makeText(this, "" + which + "::" + chkStatus, Toast.LENGTH_LONG).show()
                }
            )
            .setTitle("Choose Your Semester")
            .setCancelable(false)
            .create()
    }
}