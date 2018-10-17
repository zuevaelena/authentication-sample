package dev.sample.authentication

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakeobjects.FakeMakeSignInIntent
import dev.sample.authentication.content.ui.ContentViewModel
import dev.sample.authentication.content.usecase.DoLogOut
import dev.sample.authentication.content.usecase.FetchUser
import dev.sample.authentication.content.usecase.ObserveAuthState
import dev.sample.authentication.fakeobjects.FakeFetchSignedInUser
import dev.sample.authentication.fakeobjects.FakeFetchSignedOutUser

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.mock
import java.lang.Exception

/**
 * Unit tests for [ContentViewModel].
 */
class ContentViewModelTest {

    private val fakeMakeSignInIntent: FakeMakeSignInIntent = FakeMakeSignInIntent()

    @Mock
    private val fakeDoLogOut: DoLogOut = mock(DoLogOut::class.java)

    private val fakeSignedInUser: FetchUser = FakeFetchSignedInUser()
    private val fakeSignedOutUser: FetchUser = FakeFetchSignedOutUser()

    private lateinit var viewModel: ContentViewModel

    @Mock
    private var observeAuthState : ObserveAuthState = mock(ObserveAuthState::class.java)

    @Mock
    private val context: Context = mock(Context::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun whenLoggedOut_gettingLogInIntent_success() {
        viewModel = ContentViewModel(fakeMakeSignInIntent, fakeDoLogOut, fakeSignedOutUser, observeAuthState)

        assertThat(viewModel.getSignInIntent() is Intent, `is`(true))
    }

    @Test
    fun whenLoggedOut_doingLogout_exception() {
        viewModel = ContentViewModel(fakeMakeSignInIntent, fakeDoLogOut, fakeSignedOutUser, observeAuthState)

        try {
            viewModel.logOut(context)
            fail("Exception expected when logged out and calling logOut()")

        } catch (exception: Exception) {
            assertThat(exception is IllegalAccessException, `is`(true))
        }
    }

    @Test
    fun whenGetErrorOnLogIn_informAbout() {
        // TODO implement the test
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_exception() {
        viewModel = ContentViewModel(fakeMakeSignInIntent, fakeDoLogOut, fakeSignedInUser, observeAuthState)

        try {
            viewModel.getSignInIntent()
            fail("Exception expected when logged in and calling getSignInIntent()")

        } catch (exception: Exception) {
            assertThat(exception is IllegalAccessException, `is`(true))
        }
    }

    @Test
    fun whenAuthStateChanged_userDataRetrieved() {
        // TODO implement the test
    }

    @Test
    fun whenGetErrorOnLogOut_informAbout() {
        // TODO implement the test
    }
}
