package dev.sample.authentication

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.ui.content.ContentViewModel

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

/**
 * Unit tests for [ContentViewModel].
 */

class ContentViewModelTest {
    private val viewModel: ContentViewModel = ContentViewModel()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun initMockData() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun whenNotLoggedIn_gettingLogInIntent_success() {
        // TODO configure not logged in state

        assertThat(viewModel.getLogInIntent() is Intent, `is`(true))
    }

    @Test
    fun whenNotLoggedIn_doingLogout_failure() {
        // TODO implement the test
    }

    @Test
    fun whenGetErrorOnLogIn_informAbout() {
        // TODO implement the test
    }

    @Test
    fun whenLoggedIn_gettingLogInIntent_failure() {
        // TODO implement the test
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
