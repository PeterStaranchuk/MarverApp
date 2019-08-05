package com.ps.superheroapp.ui.character_screen.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ps.superheroapp.R

class CharacterItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val space = view.resources.getDimension(R.dimen.item_character_space).toInt()
        outRect.left = space
        outRect.top = 0
        outRect.right = space
        outRect.bottom = space
    }
}