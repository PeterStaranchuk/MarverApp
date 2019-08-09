package com.ps.superheroapp

import androidx.paging.PagedList
import com.ps.superheroapp.ui.character_screen.list.Character
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object TestPageList {

    fun <T> get(list: List<Character>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}