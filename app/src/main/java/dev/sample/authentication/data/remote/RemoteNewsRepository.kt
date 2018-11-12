package dev.sample.authentication.data.remote

import dev.sample.authentication.entities.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RemoteNewsRepository @Inject constructor(private val retrofitService: NewsApiService) {
    fun getPage(): List<News> {
        var result: List<News> = emptyList()
        runBlocking(Dispatchers.IO) {
            // TODO handle errors
            result = retrofitService.getPage().execute().body()?.articles ?: emptyList()
        }

        return result
    }
}
