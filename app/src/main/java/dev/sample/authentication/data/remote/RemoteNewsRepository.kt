package dev.sample.authentication.data.remote

import dev.sample.authentication.entities.News
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class RemoteNewsRepository @Inject constructor(private val retrofitService: NewsApiService) {

    // TODO handle network errors
    // TODO reconsider using of GlobalScope
    suspend fun getPage(): List<News> {
        return GlobalScope.async {
            retrofitService.getPage().execute().body()?.articles ?: emptyList()
        }.await()
    }
}
