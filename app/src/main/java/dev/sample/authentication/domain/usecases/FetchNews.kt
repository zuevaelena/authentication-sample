package dev.sample.authentication.domain.usecases

import androidx.paging.DataSource
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.domain.model.News
import dev.sample.authentication.presentation.screen.content.ui.ContentListDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

interface FetchNews {
    fun execute(coroutineScope: CoroutineScope, perPage: Int): DataSource.Factory<Int, News>
}

class DefaultFetchNews @Inject constructor(private val newsRepository: NewsRepository) : FetchNews {

    override fun execute(coroutineScope: CoroutineScope, perPage: Int): DataSource.Factory<Int, News> {
        return ContentListDataSourceFactory(coroutineScope, newsRepository)
    }

}
