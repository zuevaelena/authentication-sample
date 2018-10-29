package dev.sample.authentication

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakeobjects.FakeFailedSignIn
import dev.sample.authentication.fakeobjects.FakeFetchSignedInUser
import dev.sample.authentication.fakeobjects.FakeFetchSignedOutUser
import dev.sample.authentication.fakeobjects.FakeSuccessSignIn
import dev.sample.authentication.features.bottommenu.ui.BottomMenuViewModel
import dev.sample.authentication.features.bottommenu.usecase.DoLogOut
import dev.sample.authentication.features.bottommenu.usecase.SignInFailure
import dev.sample.authentication.features.bottommenu.usecase.SignInSuccess
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

/**
 * Unit tests for [BottomMenuViewModel]
 */
class BottomMenuViewModelTest {
    private lateinit var viewModel: BottomMenuViewModel

    private val fakeSuccessSignIn: FakeSuccessSignIn = FakeSuccessSignIn()
    private val fakeFailedSignIn: FakeFailedSignIn = FakeFailedSignIn()

    private val fakeSignedInUser: FetchUser = FakeFetchSignedInUser()
    private val fakeSignedOutUser: FetchUser = FakeFetchSignedOutUser()

    private val mockDoLogOut: DoLogOut = Mockito.mock(DoLogOut::class.java)
    private val mockObserveAuthState: ObserveAuthState = Mockito.mock(ObserveAuthState::class.java)

    private val mockContext: Context = Mockito.mock(Context::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun whenLoggedOut_gettingLogInIntent_success() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        MatcherAssert.assertThat(viewModel.getSignInIntent() is Intent, Matchers.`is`(true))
    }

    @Test
    fun whenLoggedOut_doingLogout_exception() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        try {
            viewModel.logOut(mockContext, {}, {})
            fail("Exception expected when logged out and calling logOut()")

        } catch (exception: Exception) {
            MatcherAssert.assertThat(exception is IllegalAccessException, Matchers.`is`(true))
        }
    }

    @Test
    fun whenLoggedOut_successfullyLogIn_returnSuccessResult() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        val signInResult = viewModel.getSignInResult(0, Mockito.mock(Intent::class.java))

        // TODO reconsider this test; change to smth more meaningful?
        MatcherAssert.assertThat(signInResult is SignInSuccess, Matchers.`is`(true))

        // TODO shall user data change also being tested here?
    }

    @Test
    fun whenDoingLogIn_gettingError_returnErrorResult() {
        viewModel = BottomMenuViewModel(fakeFailedSignIn, mockDoLogOut, fakeSignedOutUser, mockObserveAuthState)

        val signInResult = viewModel.getSignInResult(0, Mockito.mock(Intent::class.java))

        // TODO reconsider this test; change to smth more meaningful?
        MatcherAssert.assertThat(signInResult is SignInFailure, Matchers.`is`(true))

        // TODO shall user data not-change also being tested here?
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_exception() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockDoLogOut, fakeSignedInUser, mockObserveAuthState)

        try {
            viewModel.getSignInIntent()
            fail("Exception expected when logged in and calling getSignInIntent()")

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
