package dev.sample.authentication.features.content.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sample.authentication.entities.News


@BindingAdapter("items", requireAll = false)
fun bindItems(recyclerView: RecyclerView, items: List<News>? = null) {
    if(items == null) return

    (recyclerView.adapter as ContentAdapter).resetNews(items)
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, uri: Uri?) {
    Glide.with(imageView.context)
            .setDefaultRequestOptions(RequestOptions().apply {
                centerCrop()
            })
            .load(uri ?: Uri.EMPTY)
            .into(imageView)
}
