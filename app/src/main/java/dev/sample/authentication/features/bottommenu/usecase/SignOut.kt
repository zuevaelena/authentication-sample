package dev.sample.authentication.features.bottommenu.usecase

import android.content.Context
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.coroutines.resume

sealed class SignOutResult
object SignOutSuccess : SignOutResult()
object SignOutError : SignOutResult()
object SignOutCancel : SignOutResult()

interface SignOut {
    fun execute(context: Context): SignOutResult
}

class FirebaseSignOut @Inject constructor(private val firebaseAuthUi: AuthUI) : SignOut {
    private lateinit var signOutResult: SignOutResult

    override fun execute(context: Context): SignOutResult {
        runBlocking {
            try {
                withTimeout(2000) {
                    signOutResult = getSignOutResult(context)
                }

            } catch (e: TimeoutCancellationException) {
                signOutResult = SignOutError
            }
        }

        return signOutResult
    }

    private suspend fun getSignOutResult(context: Context): SignOutResult {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuthUi.signOut(context)
                    .addOnSuccessListener {
                        continuation.resume(SignOutSuccess)
                    }
                    .addOnFailureListener {
                        continuation.resume(SignOutError)
                    }
                    .addOnCanceledListener {
                        continuation.resume(SignOutCancel)
                    }
        }
    }

}
