package com.amakarkin.utxtest.di

import android.content.Context
import com.amakarkin.utxtest.data.db.AppDatabase
import com.amakarkin.utxtest.data.models.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}