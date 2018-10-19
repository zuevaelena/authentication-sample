package dev.sample.authentication.fakeobjects

import android.content.Intent
import dev.sample.authentication.feature.content.usecase.MakeSignInIntent
import org.mockito.Mockito.mock

class FakeMakeSignInIntent : MakeSignInIntent {
    override fun execute(): Intent = mock(Intent::class.java)
}
