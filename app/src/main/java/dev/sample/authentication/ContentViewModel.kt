package dev.sample.authentication

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dev.sample.authentication.model.User

class ContentViewModel: ViewModel() {
    val userData = MutableLiveData<User>()

    fun test() {
        userData.observeForever {  }
    }
}