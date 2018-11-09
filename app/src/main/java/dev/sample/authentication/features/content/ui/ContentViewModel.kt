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
        , fetchPage: FetchPage
        , observeAuthState: ObserveAuthState) : ViewModel() {

    var userData: LiveData<User>
        private set

    var newsData: LiveData<List<News>>
        private set


    init {
        userData = fetchUser.execute()
        newsData = fetchPage.execute()

        observeAuthState.start { userData = fetchUser.execute() }
    }

}
