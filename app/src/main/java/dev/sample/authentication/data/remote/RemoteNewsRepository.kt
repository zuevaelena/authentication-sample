package dev.sample.authentication.data.remote

import dev.sample.authentication.domain.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteNewsRepository @Inject constructor(private val retrofitService: NewsApiService) {

    // TODO handle network connection error somewhere
    // TODO handle network errors, that is all except with 20x response codes
    // TODO implement dispatchers factory for do not define same dispatcher twice
    fun getPage(page: Int, perPage: Int): List<News> {
        return retrofitService.getPage(page, perPage).execute().body()?.articles ?: emptyList()
    }

}
