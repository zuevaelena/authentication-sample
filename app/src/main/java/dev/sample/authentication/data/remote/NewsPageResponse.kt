package dev.sample.authentication.data.remote

import dev.sample.authentication.domain.model.News

data class NewsPageResponse(
        val status: String
        , val totalResults: Int
        , val articles: List<News>)
