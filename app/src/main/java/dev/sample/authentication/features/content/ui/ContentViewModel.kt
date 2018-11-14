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

    companion object {
        private const val FIRST_PAGE_NUMBER = 1
        private const val ITEMS_PER_PAGE = 25
    }

    private var currentPage: Int = FIRST_PAGE_NUMBER

    lateinit var userData: LiveData<User>
        private set

    lateinit var newsData: LiveData<List<News>>
        private set


    init {
        refreshUserData()
        refreshNewsData()

        observeAuthState.start { refreshUserData() }
    }

    fun refreshUserData() {
        userData = fetchUser.execute()
    }

    fun refreshNewsData() {
        currentPage = FIRST_PAGE_NUMBER
        loadCurrentPageNewsData()
    }

    fun newsNextPage() {
        currentPage++
        loadCurrentPageNewsData()
    }

    private fun loadCurrentPageNewsData() {
        newsData = fetchPage.execute(currentPage, ITEMS_PER_PAGE)
    }

}
