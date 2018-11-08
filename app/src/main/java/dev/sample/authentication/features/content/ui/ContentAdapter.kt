package dev.sample.authentication.features.content.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sample.authentication.R
import dev.sample.authentication.entities.News
import kotlinx.android.synthetic.main.item_content_news_list.view.title

class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val NEWS_ITEM = 1;
    }

    private var listNews:List<News> = emptyList()

    fun loadNews(list: List<News>) {
        listNews = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listNews.size

    override fun getItemViewType(position: Int): Int {
        return NEWS_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsItemViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_content_news_list
                , parent
                , false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(getItemViewType(position)) {
           NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(listNews[position])
       }
    }

    class NewsItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun attachData(news: News) {
            view.title.text = news.title
        }
    }
}
