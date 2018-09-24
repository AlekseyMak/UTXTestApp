package com.amakarkin.utxtest.di

import com.amakarkin.utxtest.data.models.UserDao
import com.amakarkin.utxtest.data.repository.user.UserRepositoryImpl
import com.amakarkin.utxtest.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao) : UserRepository {
        return UserRepositoryImpl(userDao)
    }
}