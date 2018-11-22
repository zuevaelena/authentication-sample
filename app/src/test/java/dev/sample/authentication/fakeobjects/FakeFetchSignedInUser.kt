package dev.sample.authentication.fakeobjects

import android.net.Uri
import dev.sample.authentication.domain.model.User
import dev.sample.authentication.domain.usecases.FetchUser
import org.mockito.Mockito.mock

class FakeFetchSignedInUser : FetchUser {
    override fun execute(forceRefresh: Boolean): User {
        return User("test_id", "test_name", mock(Uri::class.java))
    }
}
