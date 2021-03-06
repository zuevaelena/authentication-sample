package dev.sample.authentication.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.entities.User
import javax.inject.Inject


interface UserRepository {
    fun fetchUser(forceRequest: Boolean = false): LiveData<User>
}

class FirebaseUserRepository @Inject constructor(val firebaseAuth: FirebaseAuth) : UserRepository {
    private val observableData: MutableLiveData<User> = MutableLiveData()

    // TODO a proper cache, like Room database, is better to be implemented. consider this
    private var user: User? = null

    override fun fetchUser(forceRequest: Boolean): LiveData<User> {
        if (user == null || forceRequest) {
            val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
            user = User(firebaseUser?.uid ?: User.getAnonymousId()
                    , firebaseUser?.displayName ?: User.getAnonymousName()
                    , firebaseUser?.photoUrl ?: Uri.EMPTY)
        }

        return observableData.apply { postValue(user) }
    }
}
