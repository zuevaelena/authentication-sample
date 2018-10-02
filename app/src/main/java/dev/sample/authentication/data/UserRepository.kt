package dev.sample.authentication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.model.User

class UserRepository {
    private val observableData: MutableLiveData<User> = MutableLiveData()

    // TODO a proper cache is better to be implemented
    private var user: User? = null

    fun fetchUser(forceRequest: Boolean) : LiveData<User> {
        if(user == null || forceRequest) {
            val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            user = User(firebaseUser?.uid ?: User.anonymousUserId()
                    , firebaseUser?.displayName ?: User.anonymousUserName())
        }

        return observableData.apply { value = user }
    }
}
