package com.amadiyawa.incubator

import android.app.Application
import timber.log.Timber

class BabyIncubatorApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}