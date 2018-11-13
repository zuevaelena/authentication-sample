package dev.sample.authentication.features.content.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.databinding.ItemContentNewsListBinding
import dev.sample.authentication.entities.News
import java.lang.IllegalArgumentException

class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val INITIAL_ITEMS_COUNT = 1
        private const val EMPTY_ITEMS_COUNT = 1

        private const val PRELOADER_ITEM = 1
        private const val EMPTY_ITEM = 2
        private const val NEWS_ITEM = 3
    }

    private var initialLoadInProcess: Boolean = true
    private var listNews: List<News> = emptyList()

    fun resetNews(list: List<News>) {
        initialLoadInProcess = false

        listNews = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = when {
        initialLoadInProcess -> INITIAL_ITEMS_COUNT
        listNews.isEmpty() -> EMPTY_ITEMS_COUNT
        else -> listNews.size
    }

    override fun getItemViewType(position: Int): Int = when {
        initialLoadInProcess -> PRELOADER_ITEM
        listNews.isEmpty() -> EMPTY_ITEM
        else -> NEWS_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        PRELOADER_ITEM -> PreloaderViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_content_preloader
                    , parent
                    , false))

        EMPTY_ITEM -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_content_empty
                , parent
                , false))

        NEWS_ITEM -> NewsItemViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context)
                    , R.layout.item_content_news_list
                    , parent
                    , false))

        else -> throw IllegalArgumentException("Unknown view holder type = $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(listNews[position])
        }
    }

    class PreloaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class NewsItemViewHolder(private val binding: ItemContentNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun attachData(item: News) {
            binding.newsItem = item
        }
    }
}
