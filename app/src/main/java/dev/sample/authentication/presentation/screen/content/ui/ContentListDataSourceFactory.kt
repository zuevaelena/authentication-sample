package dev.sample.authentication.presentation.screen.content.ui

import androidx.paging.DataSource
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.domain.model.News
import kotlinx.coroutines.CoroutineScope

class ContentListDataSourceFactory(private val coroutineScope: CoroutineScope
                                   , private val newsRepository: NewsRepository) : DataSource.Factory<Int, News>() {

    override fun create(): DataSource<Int, News> {
        return ContentListDataSource(coroutineScope, newsRepository)
    }

}
