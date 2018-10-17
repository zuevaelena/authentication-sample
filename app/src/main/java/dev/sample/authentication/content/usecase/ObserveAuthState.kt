package dev.sample.authentication.content.usecase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Interactor (use case) for user authentication state.
 */
interface ObserveAuthState {
    fun execute()
}

class ObserveFirebaseAuthState @Inject constructor(
        private val firebaseAuth: FirebaseAuth
        , private val fetchUser: FetchUser) : ObserveAuthState {

    override fun execute() {
        firebaseAuth.addAuthStateListener { fetchUser.execute() }
    }

}