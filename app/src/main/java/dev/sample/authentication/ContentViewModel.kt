package dev.sample.authentication

import androidx.lifecycle.ViewModel
import dev.sample.authentication.model.User

class ContentViewModel : ViewModel() {
    private lateinit var userId: String

    // TODO this must became an observable
    private lateinit var userData: User

    fun init(userIdParam: String?) {
        userId = userIdParam ?: User.emptyUserId()
        initUser()
    }

    private fun initUser() : User = User(userId)

    fun getUser() : User = userData
}