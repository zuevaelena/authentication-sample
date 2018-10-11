package dev.sample.authentication.ui.content

import android.content.Intent
import androidx.lifecycle.ViewModel
import dev.sample.authentication.SignInHandler
import dev.sample.authentication.model.User
import javax.inject.Inject

/**
 * Data-handling business logic holder of Content screen.
 */
class ContentViewModel @Inject constructor(private val signInHandler: SignInHandler) : ViewModel() {

    lateinit var user: User

    init {
        // TODO get it properly
        user = User.getAnonymous()
    }

    fun getSignInIntent(): Intent {
        if(isLoggedIn()) {
            throw IllegalAccessException("Cannot get sign in intent while logged in")
        }

        return signInHandler.makeSignInIntent()
    }

    fun logOut() {
        // TODO implement
    }

    private fun isLoggedIn(): Boolean {
        return user.isLoggedIn()
    }

}