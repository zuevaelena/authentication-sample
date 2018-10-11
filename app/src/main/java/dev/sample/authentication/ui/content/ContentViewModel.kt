package dev.sample.authentication.ui.content

import android.content.Intent
import androidx.lifecycle.ViewModel
import dev.sample.authentication.SignInHandler
import javax.inject.Inject

/**
 * Data-handling business logic holder of Content screen.
 */
class ContentViewModel @Inject constructor(private val signInHandler: SignInHandler) : ViewModel() {

    fun getSignInIntent(): Intent {
        if(isLoggedIn()) {
            throw IllegalAccessException("Cannot get sign in intent while logged in")
        }

        return signInHandler.makeSignInIntent()
    }

    private fun isLoggedIn(): Boolean {
        // TODO do it in a proper way
        return false
    }

}