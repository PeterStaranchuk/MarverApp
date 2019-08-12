package com.ps.superheroapp.binding_adapters

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.ps.superheroapp.R
import com.ps.superheroapp.objects.ErrorType

@BindingAdapter("app:error")
fun setLoadFailedError(view: AppCompatTextView, error: ErrorType) {
    if (error == ErrorType.NETWORK) {
        view.text = view.context.getString(R.string.page_load_failed_error_network)
    } else {
        view.text = view.context.getString(R.string.page_load_failed_error_general)
    }
}