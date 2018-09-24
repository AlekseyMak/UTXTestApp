package com.amakarkin.utxtest

import android.app.Application
import com.amakarkin.utxtest.di.ApplicationComponent
import com.amakarkin.utxtest.di.ApplicationModule
import com.amakarkin.utxtest.di.DaggerApplicationComponent

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}