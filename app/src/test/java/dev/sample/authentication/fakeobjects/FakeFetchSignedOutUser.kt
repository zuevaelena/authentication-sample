package dev.sample.authentication.fakeobjects

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.model.User
import org.mockito.Mockito

class FakeFetchSignedOutUser : FetchUser {
    override fun execute(forceRefresh: Boolean): User {
        return User(User.getAnonymousId(), User.getAnonymousName(), Mockito.mock(Uri::class.java))
    }
}
