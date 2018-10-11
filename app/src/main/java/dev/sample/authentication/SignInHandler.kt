package dev.sample.authentication

import android.content.Intent
import com.firebase.ui.auth.AuthUI

interface SignInHandler {
    fun makeSignInIntent() : Intent
}

class FirebaseSignInHandler : SignInHandler {
    private val authUiInstance: AuthUI = AuthUI.getInstance()

    override fun makeSignInIntent() : Intent {
        val authenticationProviders = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build() // Google auth
                , AuthUI.IdpConfig.EmailBuilder().build() // auth by email
        )

        return authUiInstance
                .createSignInIntentBuilder()
                .setAvailableProviders(authenticationProviders)
                .build()
    }

}