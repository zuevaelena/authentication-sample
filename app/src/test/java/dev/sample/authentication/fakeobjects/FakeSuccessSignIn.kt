package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.domain.usecases.SignIn
import dev.sample.authentication.domain.usecases.SignInResult
import dev.sample.authentication.domain.usecases.SignInSuccess
import org.mockito.Mockito.mock

class FakeSuccessSignIn : SignIn {
    override fun makeIntent(): Intent {
        return mock(Intent::class.java)
    }

    override fun processResult(resultCode: Int, data: Intent?): SignInResult {
        return SignInSuccess
    }

}
