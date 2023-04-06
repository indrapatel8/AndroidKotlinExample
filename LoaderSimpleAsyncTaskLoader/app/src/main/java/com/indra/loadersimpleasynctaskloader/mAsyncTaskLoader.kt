package com.indra.loadersimpleasynctaskloader

import android.content.AsyncTaskLoader
import android.content.Context
import android.util.Log

class mAsyncTaskLoader(context: Context?) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
        Log.i("ISPP", "mAsyncTaskLoader : onStartLoading : forceLoad")
    }

    override fun loadInBackground(): String? {
        Log.i("ISPP", "mAsyncTaskLoader : loadInBackground")
        return "This is India, Gujarat, Mehsana : loadInBackground"
    }

    override fun deliverResult(data: String?) {
        if (isStarted()) {
            super.deliverResult(data);
            Log.i("ISPP", "mAsyncTaskLoader : deliverResult")
        }
    }

    override fun onStopLoading() {
        super.onStopLoading()
        cancelLoad()
        Log.i("ISPP", "mAsyncTaskLoader : onStopLoading : cancelLoad")
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
        Log.i("ISPP", "mAsyncTaskLoader : onReset : onStopLoading")
    }
}