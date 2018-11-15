package dev.sample.authentication.data.remote

import dev.sample.authentication.entities.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class RemoteNewsRepository @Inject constructor(private val retrofitService: NewsApiService) {

    // TODO handle network errors
    suspend fun getPage(page: Int, perPage: Int): List<News> {
        return CoroutineScope(Dispatchers.IO).async {
            retrofitService.getPage(page, perPage).execute().body()?.articles ?: emptyList()
        }.await()
    }

}
