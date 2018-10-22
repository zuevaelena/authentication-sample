package dev.sample.authentication.usecase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Interactor (use case) for user authentication state.
 */
interface ObserveAuthState {
    fun execute(onChangeComplete: () -> Unit)
}

class ObserveFirebaseAuthState @Inject constructor(private val firebaseAuth: FirebaseAuth) : ObserveAuthState {

    override fun execute(onChangeComplete: () -> Unit) {
        firebaseAuth.addAuthStateListener { onChangeComplete() }
    }

}
