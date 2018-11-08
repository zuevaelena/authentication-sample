package dev.sample.authentication.features.content.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.databinding.ItemContentNewsListBinding
import dev.sample.authentication.entities.News

class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val NEWS_ITEM = 1;
    }

    private var listNews: List<News> = emptyList()

    fun loadNews(list: List<News>) {
        listNews = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listNews.size

    override fun getItemViewType(position: Int): Int {
        return NEWS_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind: ItemContentNewsListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context)
                , R.layout.item_content_news_list
                , parent
                , false)
        return NewsItemViewHolder(bind)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(listNews[position])
        }
    }

    class NewsItemViewHolder(private val binding: ItemContentNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun attachData(item: News) {
            binding.newsItem = item
        }
    }
}
