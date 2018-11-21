package dev.sample.authentication.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Interactor (use case) for user authentication state.
 */
interface ObserveAuthState {
    fun start(onChangeComplete: () -> Unit)
    fun stop()
}

class ObserveFirebaseAuthState @Inject constructor(private val firebaseAuth: FirebaseAuth) : ObserveAuthState {
    private lateinit var listener: FirebaseAuth.AuthStateListener

    override fun start(onChangeComplete: () -> Unit) {
        listener = FirebaseAuth.AuthStateListener { onChangeComplete() }

        firebaseAuth.addAuthStateListener(listener)
    }

    override fun stop() {
        if(listener != null) {
            firebaseAuth.removeAuthStateListener(listener)
        }
    }

}
