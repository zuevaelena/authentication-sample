package dev.sample.authentication

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.fakedata.FakeSignInHandler
import dev.sample.authentication.content.ui.ContentViewModel

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.Exception

/**
 * Unit tests for [ContentViewModel].
 */
class ContentViewModelTest {

    private val fakeSignInHandler: FakeSignInHandler = FakeSignInHandler()
    private lateinit var viewModel: ContentViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() {
        viewModel = ContentViewModel(fakeSignInHandler)
    }

    @Test
    fun whenLoggedOut_gettingLogInIntent_success() {
        // TODO set logged out state

        assertThat(viewModel.getSignInIntent() is Intent, `is`(true))
    }

    @Test
    fun whenLoggedOut_doingLogout_failure() {
        // TODO implement the test
    }

    @Test
    fun whenGetErrorOnLogIn_informAbout() {
        // TODO implement the test
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_exception() {
        // TODO set logged in state

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
