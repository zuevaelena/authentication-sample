package dev.sample.authentication.presentation.screen.content.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.databinding.ItemContentNewsListBinding
import dev.sample.authentication.domain.model.News

class NewsPagedListAdapter : PagedListAdapter<News, RecyclerView.ViewHolder>(NEWS_COMPARATOR) {

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title && oldItem.source.name == newItem.source.name
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.source.name == newItem.source.name
                        && oldItem.publishedAt == newItem.publishedAt
                        && oldItem.urlToImage == newItem.urlToImage
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsItemViewHolder(NewsItemViewHolder.createBinding(parent))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsItemViewHolder).attachData(getItem(position))
    }

    class NewsItemViewHolder(private val binding: ItemContentNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun createBinding(parent: ViewGroup): ItemContentNewsListBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                            , R.layout.item_content_news_list, parent, false)
        }

        fun attachData(item: News?) {
            binding.newsItem = item
            binding.executePendingBindings()
        }
    }

}
