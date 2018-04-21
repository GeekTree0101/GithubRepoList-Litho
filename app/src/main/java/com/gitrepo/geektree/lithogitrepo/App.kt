package com.gitrepo.geektree.lithogitrepo

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.soloader.SoLoader

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        val config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()

        Fresco.initialize(this, config)
        AndroidNetworking.initialize(this)
    }
}