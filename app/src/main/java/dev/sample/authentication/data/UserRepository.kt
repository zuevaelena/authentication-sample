package dev.sample.authentication.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.model.User

class UserRepository {
    private val observableData: MutableLiveData<User> = MutableLiveData()

    // TODO a proper cache is better to be implemented, like Room database
    private var user: User? = null

    fun fetchUser(forceRequest: Boolean = false) : LiveData<User> {
        if(user == null || forceRequest) {
            val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            user = User(firebaseUser?.uid ?: User.anonymousUserId()
                    , firebaseUser?.displayName ?: User.anonymousUserName()
                    , firebaseUser?.photoUrl ?: Uri.EMPTY)
        }

        return observableData.apply { value = user }
    }
}
