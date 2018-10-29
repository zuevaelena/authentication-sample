package dev.sample.authentication

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakeobjects.FakeFetchSignedInUser
import dev.sample.authentication.fakeobjects.FakeFetchSignedOutUser
import dev.sample.authentication.fakeobjects.FakeMakeSignInIntent
import dev.sample.authentication.features.bottommenu.ui.BottomMenuViewModel
import dev.sample.authentication.features.bottommenu.usecase.DoLogOut
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import java.lang.Exception

/**
 * Unit tests for [BottomMenuViewModel]
 */
class BottomMenuViewModelTest {
    private lateinit var viewModel: BottomMenuViewModel

    private val fakeMakeSignInIntent: FakeMakeSignInIntent = FakeMakeSignInIntent()
    private val fakeSignedInUser: FetchUser = FakeFetchSignedInUser()
    private val fakeSignedOutUser: FetchUser = FakeFetchSignedOutUser()

    private val mockDoLogOut: DoLogOut = Mockito.mock(DoLogOut::class.java)
    private val mockObserveAuthState : ObserveAuthState = Mockito.mock(ObserveAuthState::class.java)
    private val mockContext: Context = Mockito.mock(Context::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun whenLoggedOut_gettingLogInIntent_success() {
        viewModel = BottomMenuViewModel(fakeMakeSignInIntent, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        MatcherAssert.assertThat(viewModel.getSignInIntent() is Intent, Matchers.`is`(true))
    }

    @Test
    fun whenLoggedOut_doingLogout_exception() {
        viewModel = BottomMenuViewModel(fakeMakeSignInIntent, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        try {
            viewModel.logOut(mockContext, {}, {})
            Assert.fail("Exception expected when logged out and calling logOut()")

        } catch (exception: Exception) {
            MatcherAssert.assertThat(exception is IllegalAccessException, Matchers.`is`(true))
        }
    }

    @Test
    fun whenLoggedOut_successfullyLogIn_userDataRetrieved() {
        TODO("implement")
    }

    @Test
    fun whenDoingLogIn_gettingError_informAbout() {
        TODO("implement")
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_exception() {
        viewModel = BottomMenuViewModel(fakeMakeSignInIntent, mockDoLogOut, fakeSignedInUser, mockObserveAuthState)

        try {
            viewModel.getSignInIntent()
            Assert.fail("Exception expected when logged in and calling getSignInIntent()")

        } catch (exception: Exception) {
            MatcherAssert.assertThat(exception is IllegalAccessException, Matchers.`is`(true))
        }
    }

    @Test
    fun whenLoggedIn_successfullyLogOut_userDataUpdated() {
        TODO("implement")
    }

    @Test
    fun whenDoingLogOut_gettingError_userDataNotUpdated() {
        TODO("implement")
    }
}
