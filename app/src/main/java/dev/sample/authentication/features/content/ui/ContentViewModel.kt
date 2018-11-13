package dev.sample.authentication.features.content.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.entities.News
import dev.sample.authentication.entities.User
import dev.sample.authentication.features.content.usecases.FetchPage
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import javax.inject.Inject

/**
 * Data-handling business logic for Content screen.
 */
class ContentViewModel @Inject constructor(
        private val fetchUser: FetchUser
        , private val fetchPage: FetchPage
        , observeAuthState: ObserveAuthState) : ViewModel() {

    lateinit var userData: LiveData<User>
        private set

    lateinit var newsData: LiveData<List<News>>
        private set


    init {
        requestUserDataRefresh()
        requestPageRefresh()

        observeAuthState.start { requestUserDataRefresh() }
    }

    fun requestUserDataRefresh() {
        userData = fetchUser.execute()
    }

    fun requestPageRefresh() {
        newsData = fetchPage.execute()
    }

}
