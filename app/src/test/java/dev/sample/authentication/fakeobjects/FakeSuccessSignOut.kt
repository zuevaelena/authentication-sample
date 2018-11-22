package dev.sample.authentication.fakeobjects

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.domain.usecases.SignOut
import dev.sample.authentication.domain.usecases.SignOutResult
import dev.sample.authentication.domain.usecases.SignOutSuccess

class FakeSuccessSignOut : SignOut {
    override fun trigger(context: Context) {
    }

    override fun getResult(): LiveData<SignOutResult> {
        return MutableLiveData<SignOutResult>().apply { value = SignOutSuccess }
    }
}
