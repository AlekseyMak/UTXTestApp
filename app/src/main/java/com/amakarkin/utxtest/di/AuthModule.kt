package com.amakarkin.utxtest.di

import com.amakarkin.utxtest.domain.auth.AuthInteractor
import com.amakarkin.utxtest.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthInteractor(userRepository: UserRepository): AuthInteractor {
        return AuthInteractor(userRepository)
    }
}