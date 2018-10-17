package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.SignInHandler
import org.mockito.Mockito.mock

class FakeSignInHandler : SignInHandler {
    override fun makeSignInIntent(): Intent = mock(Intent::class.java)
}