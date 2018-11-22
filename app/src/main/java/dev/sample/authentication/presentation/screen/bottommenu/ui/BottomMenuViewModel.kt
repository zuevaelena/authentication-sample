package dev.sample.authentication.presentation.screen.bottommenu.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dev.sample.authentication.domain.model.User
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.ObserveAuthState
import dev.sample.authentication.domain.usecases.SignIn
import dev.sample.authentication.domain.usecases.SignInResult
import dev.sample.authentication.domain.usecases.SignOut
import dev.sample.authentication.domain.usecases.SignOutResult
import javax.inject.Inject

class BottomMenuViewModel @Inject constructor(
        private val signIn: SignIn
        , private val signOut: SignOut
        , private val fetchUser: FetchUser
        , private val observeAuthState: ObserveAuthState) : ViewModel() {

    private val onAuthStateAction = { getUserData() }

    private val _userData: MutableLiveData<User> = MutableLiveData()
    val userData: LiveData<User>
        get() = _userData

    private val _signOutData: MutableLiveData<SignOutResult> = MutableLiveData()
    val signOutData: LiveData<SignOutResult>
        get() = _signOutData

    private var signOutObserver: Observer<SignOutResult> = Observer { result -> processSignOutResult(result) }

    init {
        getUserData()

        signOut.getResult().observeForever(signOutObserver)

        observeAuthState.start(onAuthStateAction)
    }

    override fun onCleared() {
        _signOutData.removeObserver(signOutObserver)

        observeAuthState.stop()

        super.onCleared()
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

    fun signOut(context: Context) {
        if (userData.value?.isLoggedIn() == false) {
            throw IllegalAccessException("Cannot log out when logged out already")
        }

        observeAuthState.stop()

        signOut.trigger(context)
    }

    private fun processSignOutResult(signOutResult: SignOutResult) {
        _signOutData.postValue(signOutResult)

        observeAuthState.start(onAuthStateAction)
    }

    private fun getUserData() {
        _userData.postValue(fetchUser.execute())
    }
}
