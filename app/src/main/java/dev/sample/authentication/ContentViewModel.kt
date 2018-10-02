package dev.sample.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.model.User

class ContentViewModel : ViewModel() {
    // TODO this must became an observable
    private var userData: User = User()

    fun init(firebaseUser: FirebaseUser?) {
        userData = User(firebaseUser?.uid ?: User.emptyUserId()
                , firebaseUser?.displayName ?: User.emptyUserName())
    }

    fun getUser() : User = userData
}