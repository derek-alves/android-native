package com.derek.wear_os_study

import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient

import androidx.wear.activity.ConfirmationActivity
import com.google.android.gms.wearable.Wearable

class MainActivity : ConfirmationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}