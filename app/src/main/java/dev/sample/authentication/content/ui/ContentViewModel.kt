package dev.sample.authentication.content.ui

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.SignInHandler
import dev.sample.authentication.content.usecase.FetchUser
import dev.sample.authentication.content.usecase.ObserveAuthState
import dev.sample.authentication.entity.User
import javax.inject.Inject

/**
 * Data-handling business logic for Content screen.
 */
class ContentViewModel @Inject constructor(
        private val signInHandler: SignInHandler
        , private val fetchUser: FetchUser
        , private val observeAuthState: ObserveAuthState) : ViewModel() {

    var userData: LiveData<User>
        private set

    init {
        userData = fetchUser.execute()
        observeAuthState.execute()
    }

    fun getSignInIntent(): Intent {
        if (userData.value?.isLoggedIn() == true) {
            throw IllegalAccessException("Cannot get sign in intent while logged in")
        }

        return signInHandler.makeSignInIntent()
    }

    fun logOut() {
        // TODO implement
    }

}