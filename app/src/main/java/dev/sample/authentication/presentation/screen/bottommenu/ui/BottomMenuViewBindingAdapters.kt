package dev.sample.authentication.presentation.screen.bottommenu.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl", "errorDrawable")
fun loadImage(imageView: ImageView, uri: Uri?, errorReplace: Int) {
    Glide.with(imageView.context)
            .setDefaultRequestOptions(RequestOptions().apply {
                error(errorReplace)
                circleCrop()
            })
            .load(uri ?: Uri.EMPTY)
            .into(imageView)
}
