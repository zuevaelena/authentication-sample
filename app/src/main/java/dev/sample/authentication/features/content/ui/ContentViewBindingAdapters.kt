package dev.sample.authentication.features.content.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sample.authentication.R
import dev.sample.authentication.entities.News


/**
 * This is the place where [ContentAdapter] getting it's items.
 */
@BindingAdapter("items", requireAll = false)
fun bindItems(recyclerView: RecyclerView, items: List<News>? = null) {
    if(items == null) return

    (recyclerView.adapter as ContentAdapter).resetNews(items)
}

// TODO consider to use okhttp to load images
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, uri: Uri?) {

    // TODO implement better placeholder
    val circularProgressDrawable = CircularProgressDrawable(imageView.context).apply {
        setColorSchemeColors(R.color.primaryDarkColor)
        strokeWidth = 5f
        centerRadius = 32f
        start()
    }

    Glide.with(imageView.context)
            .setDefaultRequestOptions(RequestOptions().apply {
                placeholder(circularProgressDrawable)
                error(R.drawable.ic_mock_photo)
                centerCrop()
            })
            .load(uri ?: Uri.EMPTY)
            .into(imageView)
}
