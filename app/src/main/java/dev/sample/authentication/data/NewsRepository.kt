package dev.sample.authentication.data

import dev.sample.authentication.data.remote.RemoteNewsRepository
import dev.sample.authentication.entities.News
import javax.inject.Inject

interface NewsRepository {
    suspend fun getPage(page: Int, perPage: Int): List<News>
}

class DefaultNewsRepository @Inject constructor(private val remoteRepository: RemoteNewsRepository) : NewsRepository {

    override suspend fun getPage(page: Int, perPage: Int): List<News> {
        return remoteRepository.getPage(page, perPage)
    }
}
