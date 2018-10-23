package dev.sample.authentication.fakeobjects

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.entities.User
import org.mockito.Mockito.mock

class FakeFetchSignedInUser : FetchUser {
    override fun execute(): LiveData<User> = MutableLiveData<User>().apply {
        value = User("test_id", "test_name", mock(Uri::class.java))
    }
}
