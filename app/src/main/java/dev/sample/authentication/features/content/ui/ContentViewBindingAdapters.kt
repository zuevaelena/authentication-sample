package dev.sample.authentication.features.content.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.entities.News


@BindingAdapter("items", requireAll = false)
fun bindItems(recyclerView: RecyclerView, items: List<News>? = null) {
    if(items == null) return

    (recyclerView.adapter as ContentAdapter).loadNews(items)
}
