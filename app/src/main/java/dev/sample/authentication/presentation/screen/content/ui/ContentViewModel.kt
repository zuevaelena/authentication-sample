package dev.sample.authentication.presentation.screen.content.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.presentation.DataLoadViewModel
import dev.sample.authentication.domain.model.News
import dev.sample.authentication.domain.model.User
import dev.sample.authentication.domain.usecases.FetchNewsPage
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.ObserveAuthState
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data-handling business logic for Content screen.
 */
class ContentViewModel @Inject constructor(
        private val fetchUser: FetchUser
        , private val fetchNewsPage: FetchNewsPage
        , private val observeAuthState: ObserveAuthState) : DataLoadViewModel() {

    companion object {
        private const val FIRST_PAGE_NUMBER = 1
        private const val ITEMS_PER_PAGE = 25
    }

    private var currentPage: Int = FIRST_PAGE_NUMBER

    private val _userData: MutableLiveData<User> = MutableLiveData()
    val userData: LiveData<User>
        get() = _userData

    private val _newsData: MutableLiveData<List<News>> = MutableLiveData()
    val newsData: LiveData<List<News>>
        get() = _newsData


    init {
        getUserData()
        refreshNewsData()

        observeAuthState.start { getUserData() }
    }

    override fun onCleared() {
        super.onCleared()

        observeAuthState.stop()
    }

    private fun getUserData() {
        _userData.postValue(fetchUser.execute())
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
        launch {
            _newsData.postValue(fetchNewsPage.execute(currentPage, ITEMS_PER_PAGE))
        }

    }

}
