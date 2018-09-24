package com.amakarkin.utxtest.presentation.ui.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SignInContract {

    interface SignInView : MvpView {

        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onSignInSuccessful()

        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onCredentialsInvalid()

        @StateStrategyType(AddToEndSingleStrategy::class)
        fun onUserBlocked()
    }
}