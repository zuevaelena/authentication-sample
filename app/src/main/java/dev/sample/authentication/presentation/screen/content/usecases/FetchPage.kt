package dev.sample.authentication.presentation.screen.content.usecases

import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.domain.model.News
import javax.inject.Inject

interface FetchPage {
    suspend fun execute(page: Int, perPage: Int): List<News>
}

class DefaultFetchPage @Inject constructor(private val newsRepository: NewsRepository) : FetchPage {

    override suspend fun execute(page: Int, perPage: Int): List<News> {
        return newsRepository.getPage(page, perPage)
    }

}
