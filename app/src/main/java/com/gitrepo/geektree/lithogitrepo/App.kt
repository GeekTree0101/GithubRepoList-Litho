package com.gitrepo.geektree.lithogitrepo

import android.app.Application
import com.facebook.soloader.SoLoader

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
    }
}