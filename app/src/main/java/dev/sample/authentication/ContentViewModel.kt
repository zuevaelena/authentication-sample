package dev.sample.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.sample.authentication.model.User

class ContentViewModel : ViewModel() {
    private var userData: MutableLiveData<User> = MutableLiveData()

    fun init() {
        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        userData.value = User(firebaseUser?.uid ?: User.emptyUserId()
                , firebaseUser?.displayName ?: User.emptyUserName())
    }

    fun getUser() : LiveData<User> = userData
}