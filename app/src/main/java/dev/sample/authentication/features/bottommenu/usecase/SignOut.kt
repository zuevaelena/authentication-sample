package dev.sample.authentication.features.bottommenu.usecase

import android.content.Context
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

sealed class SignOutResult
object SignOutSuccess : SignOutResult()
object SignOutError : SignOutResult()
object SignOutCancel : SignOutResult()

interface SignOut {
    suspend fun execute(context: Context): SignOutResult
}

class FirebaseSignOut @Inject constructor(private val firebaseAuthUi: AuthUI) : SignOut {
    override suspend fun execute(context: Context): SignOutResult {
        return getSignOutResult(context)
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
