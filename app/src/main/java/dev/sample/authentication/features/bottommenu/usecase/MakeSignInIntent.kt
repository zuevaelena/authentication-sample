package dev.sample.authentication.features.bottommenu.usecase

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

interface MakeSignInIntent {
    fun execute(): Intent
}

class MakeFirebaseSignIntent @Inject constructor(private val firebaseAuthUi: AuthUI) : MakeSignInIntent {

    override fun execute(): Intent {
        val authenticationProviders = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build() // Google auth
                , AuthUI.IdpConfig.EmailBuilder().build() // auth by email
        )

        return firebaseAuthUi
                .createSignInIntentBuilder()
                .setAvailableProviders(authenticationProviders)
                .build()
    }

}
