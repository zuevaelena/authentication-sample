package dev.sample.authentication

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakeobjects.FakeFetchSignedInUser
import dev.sample.authentication.fakeobjects.FakeFetchSignedOutUser
import dev.sample.authentication.fakeobjects.FakeMakeSignInIntent
import dev.sample.authentication.features.bottommenu.usecase.DoLogOut
import dev.sample.authentication.features.content.ui.ContentViewModel
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito.mock

/**
 * Unit tests for [ContentViewModel].
 */
class ContentViewModelTest {

    private lateinit var viewModel: ContentViewModel

    private val fakeMakeSignInIntent: FakeMakeSignInIntent = FakeMakeSignInIntent()
    private val fakeSignedInUser: FetchUser = FakeFetchSignedInUser()
    private val fakeSignedOutUser: FetchUser = FakeFetchSignedOutUser()

    private val doLogOut: DoLogOut = mock(DoLogOut::class.java)
    private val observeAuthState: ObserveAuthState = mock(ObserveAuthState::class.java)
    private val context: Context = mock(Context::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


}
