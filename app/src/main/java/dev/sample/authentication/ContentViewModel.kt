package dev.sample.authentication

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.model.User

class ContentViewModel : ViewModel() {
    private val repository: UserRepository = UserRepository()
    private lateinit var userData: LiveData<User>

    private val authStateListener: FirebaseAuth.AuthStateListener = FirebaseAuth.AuthStateListener {
        retrieveUserData()
    }

    init {
        retrieveUserData()

        FirebaseAuth.getInstance().addAuthStateListener(authStateListener)
    }

    fun getUser() : LiveData<User> = userData

    fun getSignInIntent() : Intent {
        val authenticationProviders = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build() // Google auth

                // TODO implement bellow authentication ways, consider if need to add more
                //, AuthUI.IdpConfig.EmailBuilder().build()
                //, AuthUI.IdpConfig.FacebookBuilder().build()
                //, AuthUI.IdpConfig.TwitterBuilder().build()
        )

        return AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(authenticationProviders)
                        .build()
    }

    fun logout(context: Context) {
        AuthUI.getInstance()
                .signOut(context)
    }

    private fun retrieveUserData() {
        userData = repository.fetchUser(true)
    }
}
