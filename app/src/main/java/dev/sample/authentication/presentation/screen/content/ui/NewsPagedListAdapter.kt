package dev.sample.authentication.presentation.screen.content.ui

import android.animation.AnimatorInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.databinding.ItemContentNewsListBinding
import dev.sample.authentication.domain.model.News
import java.lang.IllegalArgumentException

class NewsPagedListAdapter : PagedListAdapter<News, RecyclerView.ViewHolder>(NEWS_COMPARATOR) {

    enum class Mode { INITIAL, EMPTY, PAGING, NORMAL }

    companion object {
        private const val INITIAL_MODE_COUNT = 1
        private const val EMPTY_MODE_COUNT = 1

        private const val PRELOADER_ITEM = 1
        private const val EMPTY_ITEM = 2
        private const val NEWS_ITEM = 3
        private const val PAGING_ITEM = 4

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

    private var currentMode: Mode = Mode.NORMAL

    init {
        //initialMode()
    }

    private fun initialMode() {
        currentMode = Mode.INITIAL
        notifyDataSetChanged()
    }

    fun emptyMode() {
        currentMode = Mode.EMPTY
    }

    fun pagingMode() {
        currentMode = Mode.PAGING
    }

    fun normalMode() {
        currentMode = Mode.NORMAL
    }

    override fun getItemCount(): Int = when {
        currentMode == Mode.INITIAL -> INITIAL_MODE_COUNT
        else -> super.getItemCount()
    }


    override fun getItemViewType(position: Int): Int = when(currentMode) {
        Mode.INITIAL -> PRELOADER_ITEM
        else -> NEWS_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            PRELOADER_ITEM -> PreloaderViewHolder(PreloaderViewHolder.createView(parent))
            NEWS_ITEM -> NewsItemViewHolder(NewsItemViewHolder.createBinding(parent))
            else -> throw IllegalArgumentException("Unknown view holder type")
        }
    }

    // TODO consider to override this method too, useful for news - not necessary to re-attach data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            PRELOADER_ITEM -> (holder as PreloaderViewHolder).animateLoading()
            NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(getItem(position))
        }
    }


    class PreloaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun createView(parent: ViewGroup) : View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_content_preloader
                            , parent, false)
        }

        fun animateLoading() {
            AnimatorInflater.loadAnimator(view.context, R.animator.content_preloader)
                    .apply {
                        setTarget(view.findViewById(R.id.preloader))
                        start()
                    }
        }
    }

    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun createView(parent: ViewGroup) : View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_content_empty
                            , parent, false)
        }
    }

    class PagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun createView(parent: ViewGroup) : View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_content_paging
                            , parent, false)
        }
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
