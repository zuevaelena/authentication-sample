package dev.sample.authentication.ui.content

import android.content.Intent
import androidx.lifecycle.ViewModel
import dev.sample.authentication.FirebaseSignInHandler

/**
 * Data-handling business logic holder of Content screen.
 */
class ContentViewModel: ViewModel() {
    fun getLogInIntent(): Intent {
        if(isLoggedIn()) {
            throw IllegalAccessException("Cannot get log in intent while logged in")
        }

        // TODO inject this class by dagger, for it become mockable in tests
        return FirebaseSignInHandler().makeSignInIntent()
    }

    // TODO do it in a proper way
    private fun isLoggedIn(): Boolean {
        return false
    }

}