package com.indra.admobbannerads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
//https://developers.google.com/admob/android/quick-start
class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {
        }
        mAdView = findViewById(R.id.adView)
        mAdView.loadAd(AdRequest.Builder().build())

        //this is optional adListner
        mAdView.adListener = object : AdListener() {

            override fun onAdOpened() {
                super.onAdOpened()
                Log.i("ISPP","EVENT CALL == onAdOpened")
            }

            override fun onAdClicked() {
                super.onAdClicked()
                Log.i("ISPP","EVENT CALL == onAdClicked")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                Log.i("ISPP","EVENT CALL == onAdClosed")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                Log.i("ISPP","EVENT CALL == onAdImpression")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.i("ISPP","EVENT CALL == onAdFailedToLoad")
            }

            override fun onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked()
                Log.i("ISPP","EVENT CALL == onAdSwipeGestureClicked")
            }
        }
    }
}