package com.indra.admobinterstitialads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.indra.admobinterstitialads.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindig: ActivityMainBinding
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "ISP-P"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        initAd()
    }

    override fun onStart() {
        super.onStart()
        bindig.btnNext.apply {
            setOnClickListener(View.OnClickListener {
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this@MainActivity)
                    Log.d("TAG", "The interstitial ad SHOWWW")
                    mInterstitialAd?.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdClicked() {
                                Log.d(TAG, "Ad was clicked.")
                            }

                            override fun onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad dismissed fullscreen content.")
                                //  mInterstitialAd = null
                                startActivity(Intent(this@MainActivity, SecondPage::class.java))
                                initAd()
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                Log.e(TAG, "Ad failed to show fullscreen content.")
                            }

                            override fun onAdImpression() {
                                Log.d(TAG, "Ad recorded an impression.")
                            }

                            override fun onAdShowedFullScreenContent() {
                                Log.d(TAG, "Ad showed fullscreen content.")
                            }
                        }
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    startActivity(Intent(this@MainActivity, SecondPage::class.java))
                }
            })
        }
    }

    private fun initAd() {
        MobileAds.initialize(this)
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this@MainActivity,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    mInterstitialAd = null
                }
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }
}