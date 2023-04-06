package com.indra.loadersimpleasynctaskloader

import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() ,LoaderCallbacks<String> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loaderManager.initLoader(1, Bundle.EMPTY,this)
        Log.i("ISPP","onCreateACTIVITY")
    }

    override fun onCreateLoader(id: Int, args: Bundle?): mAsyncTaskLoader {
        Log.i("ISPP","onCreateLoader")
        return mAsyncTaskLoader(this)
    }

    override fun onLoaderReset(loader: Loader<String>) {
        Log.i("ISPP","onLoaderReset")
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        Toast.makeText(this,"onLoadFinished", Toast.LENGTH_LONG).show();
        Log.i("ISPP","onLoadFinished WITH data : "+data)
        //after loading of app success, try to change orientation of app from emulator
        // you can see only this method is repeating instead of entire life cycle methods.
    }
}