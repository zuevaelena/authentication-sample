package dev.sample.authentication.entities

import android.net.Uri
import java.util.Date

data class News(
        val source: NewsSource,
        val title: String,
        val description: String,
        val publishedAt: Date,
        val author: String = "",
        val urlToImage: String = "",
        val content: String = "") {
    fun imageUri(): Uri = Uri.parse(urlToImage ?: "") ?: Uri.EMPTY
}
