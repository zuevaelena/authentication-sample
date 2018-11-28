package dev.sample.authentication.presentation.screen.content.ui

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import dev.sample.authentication.R
import dev.sample.authentication.domain.model.News


/**
 * This is the place where [ContentAdapter] getting it's items.
 */
@BindingAdapter("items", requireAll = false)
fun bindItems(recyclerView: RecyclerView, items: PagedList<News>? = null) {
    if(items == null) return

    (recyclerView.adapter as NewsPagedListAdapter).submitList(items)
}

// TODO consider to use okhttp to load images
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, uri: Uri?) {
    Glide.with(imageView.context)
            .setDefaultRequestOptions(RequestOptions().apply {
                placeholder(R.drawable.ic_mock_photo)
                error(R.drawable.ic_mock_photo)
            })
            .load(uri ?: Uri.EMPTY)
            .transition(withCrossFade(600))
            .into(imageView)
}

/**
 * This is for text rendering optimization. Took from here:
 * https://medium.com/androiddevelopers/prefetch-text-layout-in-recyclerview-4acf9103f438
 */
@BindingAdapter("app:asyncText", "android:textSize", requireAll = false)
fun asyncText(view: TextView, text: CharSequence?, textSize: Int?) {
    if (textSize != null) {
        // interpret the text size as SP
        view.textSize = textSize.toFloat()
    }
    val params = TextViewCompat.getTextMetricsParams(view)
    (view as AppCompatTextView).setTextFuture(
            PrecomputedTextCompat.getTextFuture(text ?: "", params, null))
}
