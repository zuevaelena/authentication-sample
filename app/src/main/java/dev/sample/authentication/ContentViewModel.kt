package dev.sample.authentication

import androidx.lifecycle.ViewModel
import dev.sample.authentication.model.User

class ContentViewModel(private val userId: String?) : ViewModel() {
    private var userData: User = User()

    fun getUser() : User = userData
}