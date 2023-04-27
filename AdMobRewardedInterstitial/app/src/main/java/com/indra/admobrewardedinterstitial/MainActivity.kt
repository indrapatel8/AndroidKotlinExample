package com.indra.admobrewardedinterstitial

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.indra.admobrewardedinterstitial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private companion object {
        private const val TAG = "ISPP"
    }

    lateinit var binding: ActivityMainBinding
    private var mRewardedInterstitialAd: RewardedInterstitialAd? = null
    private var adUnitID:String = "ca-app-pub-3940256099942544/5354046379"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoadAds.setOnClickListener {
            showRewardedInterstitialAd()
        }
        binding.btnLoadAdWithProgress.setOnClickListener {
            loadAndShowRewardedInterstitialAd()
        }
        initAds()
    }

    fun initAds() {
        MobileAds.initialize(this) {
            Log.i(TAG, "MobileAds.initialize loading starts.......")
        }

//        MobileAds.setRequestConfiguration(
//            RequestConfiguration.Builder()
//                .setTestDeviceIds(
//                    listOf(
//                        "TEST-DEVICE-ID-1",
//                        "TEST-DEVICE-ID-2",
//                        "TEST-DEVICE-ID-3"
//                    )
//                ).build()
//        )
        loadRewardedAd()
    }

    fun loadRewardedAd() {
        RewardedInterstitialAd.load(
            this,
            adUnitID,
            AdRequest.Builder().build(),
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadError: LoadAdError) {
                    super.onAdFailedToLoad(loadError)
                    Log.i(
                        TAG,
                        "Error :: RewardedInterstitialAdLoadCallback :: onAdFailedToLoad :: ${loadError.message}"
                    )
                    mRewardedInterstitialAd = null
                }

                override fun onAdLoaded(rewardedInterstitialAd: RewardedInterstitialAd) {
                    super.onAdLoaded(rewardedInterstitialAd)
                    Log.i(TAG, "RewardedInterstitialAdLoadCallback::onAdLoaded::Success")
                    Toast.makeText(this@MainActivity,"Congrats! Add is Loaded Success.",Toast.LENGTH_LONG).show()
                    mRewardedInterstitialAd = rewardedInterstitialAd
                }
            }
        )
    }

    private fun showRewardedInterstitialAd() {
        if (mRewardedInterstitialAd != null) {
            Log.i(TAG, "showRewardedInterstitialAd::Loading Start......")
            mRewardedInterstitialAd!!.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.i(TAG, "FullScreenContentCallback :: Ad Clicked")
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Log.i(TAG, "FullScreenContentCallback :: onAdImpression")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        Log.i(TAG, "FullScreenContentCallback :: onAdDismissedFullScreenContent")
                        mRewardedInterstitialAd = null
                        Toast.makeText(this@MainActivity,"Opps!!! You missed 10$",Toast.LENGTH_LONG).show()
                        loadRewardedAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        Log.i(
                            TAG,
                            "FullScreenContentCallback :: onAdFailedToShowFullScreenContent :: ${p0.message}"
                        )
                        mRewardedInterstitialAd = null
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        Log.i(TAG, "FullScreenContentCallback :: onAdShowedFullScreenContent")
                    }
                }
            //show ads and listen for user reward earned
            mRewardedInterstitialAd!!.show(this) {
                //will call after used watched add full
                Log.i(TAG, "mRewardedInterstitialAd::User Watched Ads......10$ Reward Earned")
                Toast.makeText(this,"Congratulations!!! You earned 10$",Toast.LENGTH_LONG).show()
            }
        } else {
            Log.i(TAG, "mRewardedInterstitialAd::Ad Load Error......")
        }
    }

    //option2 for loadin add with progress indicator
    private fun loadAndShowRewardedInterstitialAd() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loading Rewarded Interstitial Ad")
        progressDialog.show()

        RewardedInterstitialAd.load(
            this,
            adUnitID,
            AdRequest.Builder().build(),
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadError: LoadAdError) {
                    super.onAdFailedToLoad(loadError)
                    Log.i(
                        TAG,
                        "Error :: RewardedInterstitialAdLoadCallback :: onAdFailedToLoad :: ${loadError.message}"
                    )
                    progressDialog.dismiss()
                    mRewardedInterstitialAd = null
                }

                override fun onAdLoaded(rewardedInterstitialAd: RewardedInterstitialAd) {
                    super.onAdLoaded(rewardedInterstitialAd)
                    Log.i(TAG, "RewardedInterstitialAdLoadCallback::onAdLoaded::Success")
                    mRewardedInterstitialAd = rewardedInterstitialAd
                    progressDialog.dismiss()
                    showRewardedInterstitialAd()
                }
            }
        )
    }
}



