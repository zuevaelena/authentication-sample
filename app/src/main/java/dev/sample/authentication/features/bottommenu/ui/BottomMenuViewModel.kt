package dev.sample.authentication.features.bottommenu.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.entities.User
import dev.sample.authentication.features.bottommenu.usecase.SignOut
import dev.sample.authentication.features.bottommenu.usecase.SignIn
import dev.sample.authentication.features.bottommenu.usecase.SignInResult
import dev.sample.authentication.features.bottommenu.usecase.SignOutResult
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import javax.inject.Inject

class BottomMenuViewModel @Inject constructor(
        private val signIn: SignIn
        , private val doLogOut: SignOut
        , private val fetchUser: FetchUser
        , private val observeAuthState: ObserveAuthState) : ViewModel() {

    private val onAuthStateAction = { userData = fetchUser.execute() }

    var userData: LiveData<User>
        private set

    init {
        userData = fetchUser.execute()

        observeAuthState.start(onAuthStateAction)
    }

    fun getSignInIntent(): Intent {
        if (userData.value?.isLoggedIn() == true) {
            throw IllegalAccessException("Cannot get sign in intent while logged in")
        }

        /**
         * IMPORTANT Note: if you choose to use an AuthStateListener, make sure to unregister it before
         * launching the FirebaseUI flow and re-register it after the flow returns.
         * FirebaseUI performs auth operations internally which may trigger the listener
         * before the flow is complete.
         * From here: https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#handling-the-sign-in-response
         */
        observeAuthState.stop()

        return signIn.makeIntent()
    }

    fun getSignInResult(resultCode: Int, data: Intent?): SignInResult {
        observeAuthState.start(onAuthStateAction)

        return signIn.processResult(resultCode, data)
    }

    fun signOut(context: Context) : SignOutResult {
        if (userData.value?.isLoggedIn() == false) {
            throw IllegalAccessException("Cannot log out when logged out already")
        }
        return doLogOut.execute(context)
    }
}
