package dev.sample.authentication.feature.content.usecase

import android.content.Intent
import com.firebase.ui.auth.AuthUI

interface MakeSignInIntent {
    fun execute() : Intent
}

class MakeFirebaseSignIntent : MakeSignInIntent {
    private val authUiInstance: AuthUI = AuthUI.getInstance()

    override fun execute() : Intent {
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
