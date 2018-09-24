package com.amakarkin.utxtest.di

import com.amakarkin.utxtest.presentation.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class,
                        RoomModule::class,
                        RepositoryModule::class,
                        AuthModule::class])

interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}