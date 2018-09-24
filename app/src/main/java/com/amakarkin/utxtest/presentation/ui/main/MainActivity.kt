package com.amakarkin.utxtest.presentation.ui.main

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.amakarkin.utxtest.App
import com.amakarkin.utxtest.R
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), SignInContract.SignInView {

    @Inject
    @InjectPresenter
    lateinit var signInPresenter: SignInPresenter

    @ProvidePresenter
    fun provideSignInPresenter(): SignInPresenter {
        return signInPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signInPresenter.addMockUsers()

        findViewById<View>(R.id.btnSignIn).setOnClickListener {
            val userType = findViewById<Spinner>(R.id.userTypeSpinner).selectedItem as String
            val userName = findViewById<EditText>(R.id.usernameInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()
            signInPresenter.signIn(userType, userName, password)
        }
    }

    private fun inject() {
        App.applicationComponent.inject(this)
    }

    override fun onCredentialsInvalid() {
        showMessage(R.string.message_invalid_credentials)
    }

    override fun onUserBlocked() {
        showMessage(R.string.message_user_blocked)
    }

    override fun onSignInSuccessful() {
        showMessage(R.string.message_sign_in_successful)
    }

    private fun showMessage(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
