package dev.sample.authentication.fakeobjects

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOut
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOutError
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOutResult

class FakeFailedSignOut : SignOut {
    override fun trigger(context: Context) {
    }

    override fun getResult(): LiveData<SignOutResult> {
        return MutableLiveData<SignOutResult>().apply { value = SignOutError }
    }

}
