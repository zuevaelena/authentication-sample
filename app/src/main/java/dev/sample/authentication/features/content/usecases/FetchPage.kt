package dev.sample.authentication.features.content.usecases

import androidx.lifecycle.LiveData
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.entities.News
import javax.inject.Inject

interface FetchPage {
    fun execute() : LiveData<List<News>>
}

class FetchNewsApiPage @Inject constructor(private val newsRepository: NewsRepository) : FetchPage {
    override fun execute(): LiveData<List<News>> {
       return newsRepository.getPage()
    }
}
