package dev.sample.authentication.features.content.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sample.authentication.R
import dev.sample.authentication.entities.News
import kotlinx.android.synthetic.main.item_content_news_list.view.description
import kotlinx.android.synthetic.main.item_content_news_list.view.photo
import kotlinx.android.synthetic.main.item_content_news_list.view.publish_date
import kotlinx.android.synthetic.main.item_content_news_list.view.title

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
        return NewsItemViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_content_news_list
                , parent
                , false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            NEWS_ITEM -> (holder as NewsItemViewHolder).attachData(listNews[position])
        }
    }

    class NewsItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun attachData(news: News) {
            view.title.text = news.title
            view.description.text = news.description
            view.publish_date.text = news.publishedAt.toString()

            Glide.with(view.context)
                    .setDefaultRequestOptions(RequestOptions().apply {
                        error(R.drawable.ic_account_circle)
                        centerCrop()
                    })
                    .load(news.imageUri)
                    .into(view.photo)
        }
    }
}
