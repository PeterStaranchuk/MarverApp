package com.ps.superheroapp.ui.character_screen

import android.view.View
import androidx.databinding.BindingAdapter
import com.ps.superheroapp.objects.ViewVisibility

@BindingAdapter("android:visibility")
fun setViewVisibility(view: View, visibility: ViewVisibility) {
    when (visibility) {
        ViewVisibility.VISIBLE -> view.visibility = View.VISIBLE
        ViewVisibility.INVISIBLE -> view.visibility = View.INVISIBLE
        ViewVisibility.GONE -> view.visibility = View.GONE
    }
}