package dev.sample.authentication

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakeobjects.FakeFailedSignIn
import dev.sample.authentication.fakeobjects.FakeFailedSignOut
import dev.sample.authentication.fakeobjects.FakeFetchSignedInUser
import dev.sample.authentication.fakeobjects.FakeFetchSignedOutUser
import dev.sample.authentication.fakeobjects.FakeSuccessSignIn
import dev.sample.authentication.fakeobjects.FakeSuccessSignOut
import dev.sample.authentication.features.bottommenu.ui.BottomMenuViewModel
import dev.sample.authentication.features.bottommenu.usecase.SignIn
import dev.sample.authentication.features.bottommenu.usecase.SignOut
import dev.sample.authentication.features.bottommenu.usecase.SignInFailure
import dev.sample.authentication.features.bottommenu.usecase.SignInSuccess
import dev.sample.authentication.features.bottommenu.usecase.SignOutError
import dev.sample.authentication.features.bottommenu.usecase.SignOutSuccess
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

    private val fakeSuccessSignOut: FakeSuccessSignOut = FakeSuccessSignOut()
    private val fakeFailureSignOut: FakeFailedSignOut = FakeFailedSignOut()

    private val mockSignIn: SignIn = Mockito.mock(SignIn::class.java)
    private val mockSignOut: SignOut = Mockito.mock(SignOut::class.java)

    private val mockObserveAuthState: ObserveAuthState = Mockito.mock(ObserveAuthState::class.java)

    private val mockContext: Context = Mockito.mock(Context::class.java)
    private val mockIntent: Intent = Mockito.mock(Intent::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun whenLoggedOut_gettingLogInIntent_success() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockSignOut, fakeSignedOutUser, mockObserveAuthState)

        MatcherAssert.assertThat(viewModel.getSignInIntent() is Intent, Matchers.`is`(true))
    }

    @Test
    fun whenLoggedOut_doingLogout_exception() {
        viewModel = BottomMenuViewModel(mockSignIn, fakeSuccessSignOut, fakeSignedOutUser, mockObserveAuthState)

        try {
            viewModel.signOut(mockContext)
            fail("Exception expected when logged out and calling signOut()")

        } catch (exception: Exception) {
            MatcherAssert.assertThat(exception is IllegalAccessException, Matchers.`is`(true))
        }
    }

    @Test
    fun whenLoggedOut_successfullyLogIn_returnSuccessResult() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockSignOut, fakeSignedOutUser, mockObserveAuthState)

        val signInResult = viewModel.getSignInResult(0, mockIntent)

        MatcherAssert.assertThat(signInResult is SignInSuccess, Matchers.`is`(true))
    }

    @Test
    fun whenDoingLogIn_gettingError_returnErrorResult() {
        viewModel = BottomMenuViewModel(fakeFailedSignIn, mockSignOut, fakeSignedOutUser, mockObserveAuthState)

        val signInResult = viewModel.getSignInResult(0, mockIntent)

        MatcherAssert.assertThat(signInResult is SignInFailure, Matchers.`is`(true))
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_exception() {
        viewModel = BottomMenuViewModel(fakeSuccessSignIn, mockSignOut, fakeSignedInUser, mockObserveAuthState)

        try {
            viewModel.getSignInIntent()
            fail("Exception expected when logged in and calling getSignInIntent()")

        } catch (exception: Exception) {
            MatcherAssert.assertThat(exception is IllegalAccessException, Matchers.`is`(true))
        }
    }

    @Test
    fun whenLoggedIn_successfullyLogOut_returnSuccessResult() {
        viewModel = BottomMenuViewModel(mockSignIn, fakeSuccessSignOut, fakeSignedInUser, mockObserveAuthState)

        viewModel.signOut(mockContext);

        val signOutData = viewModel.signoutData
        MatcherAssert.assertThat(signOutData.value is SignOutSuccess, Matchers.`is`(true))
    }

    @Test
    fun whenDoingLogOut_gettingError_returnErrorResult() {
        viewModel = BottomMenuViewModel(mockSignIn, fakeFailureSignOut, fakeSignedInUser, mockObserveAuthState)

        viewModel.signOut(mockContext);

        val signOutData = viewModel.signoutData
        MatcherAssert.assertThat(signOutData.value is SignOutError, Matchers.`is`(true))
    }
}
