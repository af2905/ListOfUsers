package com.github.af2905.listofusers.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.af2905.listofusers.R

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:url")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder_error)
                        .fitCenter()
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}