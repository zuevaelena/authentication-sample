package dev.sample.authentication.data.remote

import dev.sample.authentication.entities.News

data class NewsPageResponse(
        val status: String
        , val totalResults: Int
        , val articles: List<News>)
