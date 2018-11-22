package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.domain.usecases.SignIn
import dev.sample.authentication.domain.usecases.SignInError
import dev.sample.authentication.domain.usecases.SignInFailure
import dev.sample.authentication.domain.usecases.SignInResult
import org.mockito.Mockito

class FakeFailedSignIn : SignIn {
    override fun makeIntent(): Intent {
        return Mockito.mock(Intent::class.java)
    }

    override fun processResult(resultCode: Int, data: Intent?): SignInResult {
        return SignInFailure(SignInError.UNKNOWN_ERROR)
    }

}
