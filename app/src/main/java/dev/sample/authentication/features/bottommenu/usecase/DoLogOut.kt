package dev.sample.authentication.features.bottommenu.usecase

import android.content.Context
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

interface DoLogOut {
    fun execute(context: Context)
}

class DoFirebaseLogOut @Inject constructor(private val firebaseAuthUi: AuthUI) : DoLogOut {
    override fun execute(context: Context) {
        firebaseAuthUi.signOut(context)
    }
}
