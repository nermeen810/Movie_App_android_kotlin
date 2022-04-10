package com.nermeen.movie_app.ui.details.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nermeen.movie_app.utils.Constants


@BindingAdapter("app:setImageURL")
fun setImageByURL(image: ImageView, url: String) {
    Glide.with(image.context)
        .load(Constants.createImageUrl(url))
        .into(image)
}