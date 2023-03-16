package com.indra.asynctaskdemo

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var btnDownload: Button
    lateinit var tvMessage: TextView
    lateinit var progressDialog: ProgressDialog
    lateinit var myasyncTask: myAsyncTask
    var step: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    private fun initComponent() {
        myasyncTask = myAsyncTask()
        btnDownload = findViewById(R.id.btnDownload)
        tvMessage = findViewById(R.id.tvMessage)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Downloading.....")
        progressDialog.setMessage("Please wait")
        progressDialog.setCancelable(false)

        btnDownload.setOnClickListener {
            myasyncTask.execute()
        }
    }

    inner class myAsyncTask : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.show()
            Log.i("ISPP", "onPreExecute Calling.....")
            step = 0
        }

        override fun doInBackground(vararg params: String?): String {
            Log.i("ISPP", "doInBackground Calling.....")
            while (step < 100) {
                TimeUnit.MILLISECONDS.sleep(100)
                step++
                publishProgress(step);
            }
            return "SUCCESS"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            progressDialog.setMessage("Please wait " + values[0] + "%")
            Log.i("ISPP", "step....." + values[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            Log.i("ISPP", "onPostExecute Calling....." + result)
        }
    }
}