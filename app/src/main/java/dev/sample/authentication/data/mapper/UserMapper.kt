package dev.sample.authentication.data.mapper

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.domain.model.User

class UserMapper {
    companion object {
        fun firebaseToModel(firebaseUser: FirebaseUser?) : User {
            return User(firebaseUser?.uid ?: User.getAnonymousId()
                    , firebaseUser?.displayName ?: User.getAnonymousName()
                    , firebaseUser?.photoUrl ?: Uri.EMPTY)
        }
    }
}
