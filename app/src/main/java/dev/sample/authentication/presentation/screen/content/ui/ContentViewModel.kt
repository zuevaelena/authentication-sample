package dev.sample.authentication.presentation.screen.content.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.sample.authentication.domain.model.News
import dev.sample.authentication.domain.model.User
import dev.sample.authentication.domain.usecases.FetchNews
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.ObserveAuthState
import dev.sample.authentication.presentation.DataLoadViewModel
import javax.inject.Inject


/**
 * Data-handling business logic for Content screen.
 */
class ContentViewModel @Inject constructor(
        private val fetchUser: FetchUser
        , private val fetchNews: FetchNews
        , private val observeAuthState: ObserveAuthState) : DataLoadViewModel() {

    companion object {
        private const val ITEMS_PER_PAGE = 10

        private val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ITEMS_PER_PAGE)
                .build()
    }

    private val _userData: MutableLiveData<User> = MutableLiveData()
    val userData: LiveData<User>
        get() = _userData

    val newsData: LiveData<PagedList<News>>



    init {
        getUserData()
        observeAuthState.start { getUserData() }

        newsData = LivePagedListBuilder(fetchNews.execute(this), pagedListConfig)
                .build()
    }

    override fun onCleared() {
        super.onCleared()

        observeAuthState.stop()
    }

    fun refreshNewsData() {
        newsData.value?.dataSource?.invalidate()
    }

    private fun getUserData() {
        _userData.postValue(fetchUser.execute())
    }


}
