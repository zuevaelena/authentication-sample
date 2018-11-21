package dev.sample.authentication.presentation.screen.bottommenu.usecase

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import dev.sample.authentication.R
import javax.inject.Inject

enum class SignInError {
    NO_NETWORK, UNKNOWN_ERROR
}

sealed class SignInResult
object SignInSuccess : SignInResult()
data class SignInFailure(val error: SignInError) : SignInResult()

interface SignIn {
    fun makeIntent() : Intent
    fun processResult(resultCode: Int, data: Intent?) : SignInResult
}

class FirebaseSignIn @Inject constructor(private val firebaseAuthUi: AuthUI) : SignIn {

    override fun makeIntent() : Intent {
        val authenticationProviders = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build() // Google auth
                , AuthUI.IdpConfig.EmailBuilder().build() // auth by email
        )

        return firebaseAuthUi
                .createSignInIntentBuilder()
                .setAvailableProviders(authenticationProviders)
                .setTheme(R.style.AppTheme)
                .build()
    }

    override fun processResult(resultCode: Int, data: Intent?) : SignInResult {
        val response: IdpResponse? = IdpResponse.fromResultIntent(data)

        if(resultCode != Activity.RESULT_OK && response != null) {
            val failure: SignInFailure = when(response.error?.errorCode) {
                ErrorCodes.NO_NETWORK -> SignInFailure(SignInError.NO_NETWORK)
                else -> SignInFailure(SignInError.UNKNOWN_ERROR)
            }
            return failure
        }

        return SignInSuccess
    }

}
