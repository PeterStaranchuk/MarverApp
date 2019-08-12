package com.ps.superheroapp.objects

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class RestorableLoadViewModel : ViewModel() {

    val error = ObservableField<ErrorType>()
    val tryAgainVisibility = ObservableField(ViewVisibility.GONE)

    fun onTryAgainPressed() {
        tryAgainVisibility.set(ViewVisibility.GONE)
        performTryAgainAction()
    }

    abstract fun performTryAgainAction()
}