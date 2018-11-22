package dev.sample.authentication.presentation.screen.content.ui

import android.animation.AnimatorInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.databinding.ItemContentNewsListBinding
import dev.sample.authentication.domain.model.News
import java.lang.IllegalArgumentException


class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val FIRST_POSITION_INDEX = 0

        private const val INITIAL_ITEMS_COUNT = 1
        private const val EMPTY_ITEMS_COUNT = 1

        private const val PRELOADER_ITEM = 1
        private const val EMPTY_ITEM = 2
        private const val NEWS_ITEM = 3
        private const val PAGING_ITEM = 4
    }

    private var initialLoadInProcess: Boolean = false
    private var pagingInProcess: Boolean = false
    private lateinit var listNews: MutableList<News>

    init {
        initialMode()
    }

    fun initialMode() {
        initialLoadInProcess = true
        pagingInProcess = false
        listNews = mutableListOf()

        notifyDataSetChanged()
    }

    fun refreshMode() {
        listNews = mutableListOf()
    }

    fun pagingMode() {
        pagingInProcess = true

        notifyItemInserted(itemCount - 1)
    }

    fun addItems(list: List<News>) {
        listNews.addAll(list)

        if(initialLoadInProcess) {
            initialLoadInProcess = false

            notifyItemChanged(FIRST_POSITION_INDEX)
            notifyItemRangeInserted(FIRST_POSITION_INDEX + 1, list.size-1)

        } else if(pagingInProcess) {
            val pagingPosition: Int = itemCount - 1

            pagingInProcess = false

            notifyItemChanged(pagingPosition)
            notifyItemRangeInserted(pagingPosition + 1, list.size-1)

        // important to do so on refresh mode, since in refreshMode() data set change wasn't notified
        } else if(listNews.size == list.size) {
            notifyDataSetChanged()

        } else {
            notifyItemRangeInserted(itemCount - 1, list.size)
        }
    }

    override fun getItemCount(): Int = when {
        initialLoadInProcess -> INITIAL_ITEMS_COUNT
        listNews.isEmpty() -> EMPTY_ITEMS_COUNT
        else -> listNews.size + ( if(pagingInProcess) 1 else 0 )
    }

    override fun getItemViewType(position: Int): Int = when {
        initialLoadInProcess -> PRELOADER_ITEM
        listNews.isEmpty() -> EMPTY_ITEM
        pagingInProcess && position == listNews.size -> PAGING_ITEM
        else -> NEWS_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        PRELOADER_ITEM -> PreloaderViewHolder(PreloaderViewHolder.createView(parent))
        EMPTY_ITEM -> EmptyViewHolder(EmptyViewHolder.createView(parent))
        NEWS_ITEM -> NewsItemViewHolder(NewsItemViewHolder.createBinding(parent))
        PAGING_ITEM -> PagingViewHolder(PagingViewHolder.createView(parent))
        else -> throw IllegalArgumentException("Unknown view holder type = $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            PRELOADER_ITEM -> (holder as PreloaderViewHolder).animateLoading()
            NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(listNews[position])
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

    class NewsItemViewHolder(private val binding: ItemContentNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun createBinding(parent: ViewGroup): ItemContentNewsListBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                            , R.layout.item_content_news_list, parent, false)
        }

        fun attachData(item: News) {
            binding.newsItem = item
            binding.executePendingBindings()
        }
    }

    class PagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun createView(parent: ViewGroup) : View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_content_paging
                            , parent, false)
        }
    }
}
