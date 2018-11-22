package dev.sample.authentication.data

import com.google.firebase.auth.FirebaseAuth
import dev.sample.authentication.data.mapper.UserMapper
import dev.sample.authentication.domain.model.User
import javax.inject.Inject


interface UserRepository {
    fun fetchUser(): User
}

class FirebaseUserRepository @Inject constructor(val firebaseAuth: FirebaseAuth) : UserRepository {

    override fun fetchUser(): User {
        return UserMapper.firebaseToModel(firebaseAuth.currentUser)
    }

}
