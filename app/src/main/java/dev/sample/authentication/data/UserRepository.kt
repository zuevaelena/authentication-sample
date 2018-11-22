package dev.sample.authentication.data

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.domain.model.User
import javax.inject.Inject


interface UserRepository {
    fun fetchUser(): User
}

class FirebaseUserRepository @Inject constructor(val firebaseAuth: FirebaseAuth) : UserRepository {

    override fun fetchUser(): User {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        return User(firebaseUser?.uid ?: User.getAnonymousId()
                , firebaseUser?.displayName ?: User.getAnonymousName()
                , firebaseUser?.photoUrl ?: Uri.EMPTY)
    }

}
