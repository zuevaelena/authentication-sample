package dev.sample.authentication.features.bottommenu.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.entities.User
import dev.sample.authentication.features.bottommenu.usecase.DoLogOut
import dev.sample.authentication.features.bottommenu.usecase.MakeSignInIntent
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import javax.inject.Inject

class BottomMenuViewModel @Inject constructor(
        private val makeSignInIntent: MakeSignInIntent
        , private val doLogOut: DoLogOut
        , private val fetchUser: FetchUser
        , observeAuthState: ObserveAuthState) : ViewModel() {

    var userData: LiveData<User>
        private set

    init {
        userData = fetchUser.execute()

        /**
         * TODO:
         * Note: if you choose to use an AuthStateListener, make sure to unregister it before
         * launching the FirebaseUI flow and re-register it after the flow returns.
         * FirebaseUI performs auth operations internally which may trigger the listener
         * before the flow is complete.
         */
        observeAuthState.execute { userData = fetchUser.execute() }
    }

    fun getSignInIntent(): Intent {
        if (userData.value?.isLoggedIn() == true) {
            throw IllegalAccessException("Cannot get sign in intent while logged in")
        }

        return makeSignInIntent.execute()
    }

    fun logOut(context: Context, onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (userData.value?.isLoggedIn() == false) {
            throw IllegalAccessException("Cannot log out when logged out already")
        }
        return doLogOut.execute(context, onSuccess, onFailure)
    }
}
