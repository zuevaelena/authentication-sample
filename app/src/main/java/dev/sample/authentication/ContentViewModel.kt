package dev.sample.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.model.User

class ContentViewModel: ViewModel() {
    val userData = MutableLiveData<User>()

    fun test() {
        userData.observeForever {  }
    }
}