package dev.sample.authentication.features.content.usecases

import androidx.lifecycle.LiveData
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.entities.News
import javax.inject.Inject

interface FetchPage {
    fun execute(page: Int, perPage: Int) : LiveData<List<News>>
}

class FetchNewsApiPage @Inject constructor(private val newsRepository: NewsRepository) : FetchPage {

    override fun execute(page: Int, perPage: Int): LiveData<List<News>> {
       return newsRepository.getPage(page, perPage)
    }

}
