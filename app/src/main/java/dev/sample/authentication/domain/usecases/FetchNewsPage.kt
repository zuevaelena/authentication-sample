package dev.sample.authentication.domain.usecases

import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.domain.model.News
import javax.inject.Inject

interface FetchNewsPage {
    suspend fun execute(page: Int, perPage: Int): List<News>
}

class DefaultFetchNewsPage @Inject constructor(private val newsRepository: NewsRepository) : FetchNewsPage {

    override suspend fun execute(page: Int, perPage: Int): List<News> {
        try {
            return newsRepository.getPage(page, perPage)

        } catch (e: Exception) {
            return emptyList()
        }
    }

}
