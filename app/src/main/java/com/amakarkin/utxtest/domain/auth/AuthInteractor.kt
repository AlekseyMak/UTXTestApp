package com.amakarkin.utxtest.domain.auth

import android.arch.persistence.room.EmptyResultSetException
import android.util.Log
import com.amakarkin.utxtest.data.models.User
import com.amakarkin.utxtest.domain.user.UserRepository
import io.reactivex.Single

class AuthInteractor(private val userRepository: UserRepository) {

    companion object {
        private val TAG = "AuthInteractor"

        private val MAX_LOGIN_ATTEMPTS = 3
    }

    fun login(userType: String, login: String, password: String): Single<User> {
        return userRepository.getUserData(login)
                .doOnError {
                    if (it is EmptyResultSetException) {
                        throw InvalidCredentialsError()
                    } else {
                        throw it
                    }
                }
                .doOnSuccess { user ->
                    if (user.isBlocked) {
                        throw UserBlockedError()
                    }
                }
                .doOnSuccess { user ->
                    if (user.password != password || user.type != userType) {
                        onFailToLogin(user)
                        throw InvalidCredentialsError()
                    }
                }
                .doOnSuccess {
                    onSuccessfulLogin(it)
                }
    }

    private fun onSuccessfulLogin(user: User) {
        user.failedAttempts = 0
        userRepository.updateUser(user)
                .subscribe({
                    Log.i(TAG, "User updated")
                }, {
                    Log.e(TAG, it.message)
                })
    }

    private fun onFailToLogin(user: User) {
        user.failedAttempts++
        if (user.failedAttempts > MAX_LOGIN_ATTEMPTS) {
            user.isBlocked = true
        }
        userRepository.updateUser(user)
                .subscribe({
                    Log.i(TAG, "User updated")
                }, {
                    Log.e(TAG, it.message)
                })
    }
}