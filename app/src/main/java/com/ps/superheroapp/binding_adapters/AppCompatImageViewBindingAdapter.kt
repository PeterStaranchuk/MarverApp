package com.ps.superheroapp.binding_adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.ps.superheroapp.extensions.getApp

@BindingAdapter("image_url")
fun setImageFromUrl(view: AppCompatImageView, url: String) {
    view.getApp().picasso.load(url).into(view)
}

