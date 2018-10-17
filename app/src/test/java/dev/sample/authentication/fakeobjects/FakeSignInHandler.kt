package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.SignInHandler

class FakeSignInHandler : SignInHandler {
    override fun makeSignInIntent(): Intent {
        return Intent()
    }
}