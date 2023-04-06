package com.indra.cursorloader

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager.LoaderCallbacks
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.indra.cursorloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),LoaderCallbacks<Cursor> {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("INDRA", "MainActivity : onCreate")
        supportLoaderManager.initLoader(1, null, this@MainActivity)
    }

    override fun onStart() {
        super.onStart()
        Log.i("INDRA", "MainActivity : onStart")
        binding.apply {
            btnLoadContacts.setOnClickListener {
                Toast.makeText(this@MainActivity, "Worked", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Log.i("INDRA", "onCreateLoader")
        return CursorLoader(this,
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        Log.i("INDRA", "onLoaderReset")
    }

    @SuppressLint("Range")
    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
        Log.i("INDRA", "onLoadFinished")
        if(cursor?.moveToFirst()==true){
            do {
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                Thread.sleep(2000)
                Log.i("INDRA", name)
            }while(cursor.moveToNext())
        }
    }
}
//https://google-developer-training.github.io/android-developer-fundamentals-course-concepts/en/Unit%204/121_c_loaders.html
//https://www.youtube.com/watch?v=Iz1_pf3YK2c