package dev.sample.authentication.entities

import android.net.Uri

data class NewsSource(
        val id: String,
        val name: String,
        val url: Uri = Uri.EMPTY,
        val category: String = "",
        val language: String = "",
        val country: String = ""
)
