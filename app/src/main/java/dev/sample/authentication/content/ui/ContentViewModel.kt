package dev.sample.authentication.content.ui

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.SignInHandler
import dev.sample.authentication.model.User
import javax.inject.Inject

/**
 * Data-handling business logic holder of Content screen.
 */
class ContentViewModel @Inject constructor(private val signInHandler: SignInHandler) : ViewModel() {

    var userData: LiveData<User> = MutableLiveData<User>().apply { value = User.getAnonymous() }
        private set

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