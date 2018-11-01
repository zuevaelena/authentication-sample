package dev.sample.authentication.features.bottommenu.usecase

import android.content.Context
import android.util.Log
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
            Log.v("start blocking", Thread.currentThread().name)
            val job = GlobalScope.launch {
                signOutResult = getSignOutResult(context)
            }
            job.join()
            Log.v("end blocking", Thread.currentThread().name)
        }

        return signOutResult
    }

    private suspend fun getSignOutResult(context: Context): SignOutResult {
        return suspendCoroutine { continuation ->
            Log.v("blocking continue 2", Thread.currentThread().name)
            firebaseAuthUi.signOut(context)
                    .addOnSuccessListener {
                        Log.v("should end blocking 1", Thread.currentThread().name)
                        continuation.resume(SignOutSuccess)
                    }
                    .addOnFailureListener {
                        Log.v("should end blocking 2", Thread.currentThread().name)
                        continuation.resume(SignOutError)
                    }
                    .addOnCanceledListener {
                        Log.v("should end blocking 3", Thread.currentThread().name)
                        continuation.resume(SignOutCancel)
                    }
        }
    }

}
