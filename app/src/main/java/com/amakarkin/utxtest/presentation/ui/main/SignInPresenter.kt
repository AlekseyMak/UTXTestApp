package com.amakarkin.utxtest.presentation.ui.main

import android.util.Log
import com.amakarkin.utxtest.data.models.User
import com.amakarkin.utxtest.domain.auth.AuthInteractor
import com.amakarkin.utxtest.domain.auth.InvalidCredentialsError
import com.amakarkin.utxtest.domain.auth.UserBlockedError
import com.amakarkin.utxtest.domain.user.UserRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class SignInPresenter @Inject constructor(
        private val userRepository: UserRepository,
        private val authInteractor: AuthInteractor
): MvpPresenter<SignInContract.SignInView>() {

    companion object {
        private val TAG = "SignInPresenter"
    }

    fun addMockUsers() {
        val admin = User("admin", "Admin", "adminpass", false, 0)
        val regularUser = User("user", "Regular User", "userpass", false, 0)
        userRepository.addUsers(listOf(admin, regularUser))
                .subscribe({
                    Log.i(TAG, "Inserted")
                }, {
                    Log.e(TAG, it.message)
                })
    }

    fun signIn(userType: String, userName: String, password: String) {
        if (userType.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            viewState.onCredentialsInvalid()
            return
        }

        authInteractor.login(userType, userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.onSignInSuccessful()
                }, {
                    if (it is InvalidCredentialsError) {
                        viewState.onCredentialsInvalid()
                    } else if (it is UserBlockedError) {
                        viewState.onUserBlocked()
                    }
                })

    }

}