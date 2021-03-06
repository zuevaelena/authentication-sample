package dev.sample.authentication.features.bottommenu.usecase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

sealed class SignOutResult
object SignOutSuccess : SignOutResult()
object SignOutError : SignOutResult()
object SignOutCancel : SignOutResult()

interface SignOut {
    fun trigger(context: Context)
    fun getResult(): LiveData<SignOutResult>
}

class FirebaseSignOut @Inject constructor(private val firebaseAuthUi: AuthUI) : SignOut {
    private var signOutResult: MutableLiveData<SignOutResult> = MutableLiveData()

    override fun trigger(context: Context) {
        firebaseAuthUi.signOut(context)
                .addOnSuccessListener {
                    signOutResult.postValue(SignOutSuccess)
                }
                .addOnFailureListener {
                    signOutResult.postValue(SignOutError)
                }
                .addOnCanceledListener {
                    signOutResult.postValue(SignOutCancel)
                }
    }

    override fun getResult(): LiveData<SignOutResult> {
        return signOutResult
    }

}
