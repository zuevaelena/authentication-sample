package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignIn
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignInResult
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignInSuccess
import org.mockito.Mockito.mock

class FakeSuccessSignIn : SignIn {
    override fun makeIntent(): Intent {
        return mock(Intent::class.java)
    }

    override fun processResult(resultCode: Int, data: Intent?): SignInResult {
        return SignInSuccess
    }

}
