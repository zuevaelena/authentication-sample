package dev.sample.authentication.presentation.screen.content.ui

import androidx.paging.PageKeyedDataSource
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.domain.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class ContentListDataSource(private val coroutineScope: CoroutineScope
                            , private val newsRepository: NewsRepository) : PageKeyedDataSource<Int, News>() {
    companion object {
        private const val FIRST_PAGE_NUMBER = 1
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        coroutineScope.launch {
            val newsList = newsRepository.getPage(FIRST_PAGE_NUMBER, params.requestedLoadSize)
            callback.onResult(newsList, null, FIRST_PAGE_NUMBER.inc())
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        coroutineScope.launch {
            val newsList = newsRepository.getPage(params.key, params.requestedLoadSize)
            callback.onResult(newsList, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
    }
}
