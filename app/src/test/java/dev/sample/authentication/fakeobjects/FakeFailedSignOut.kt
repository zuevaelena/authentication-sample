package dev.sample.authentication.fakeobjects

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.features.bottommenu.usecase.SignOut
import dev.sample.authentication.features.bottommenu.usecase.SignOutError
import dev.sample.authentication.features.bottommenu.usecase.SignOutResult

class FakeFailedSignOut : SignOut {
    override fun trigger(context: Context) {
    }

    override fun getResult(): LiveData<SignOutResult> {
        return MutableLiveData<SignOutResult>().apply { value = SignOutError }
    }

}
