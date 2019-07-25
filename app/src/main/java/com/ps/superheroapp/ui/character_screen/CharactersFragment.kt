package com.ps.superheroapp.ui.character_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps.superheroapp.databinding.FragmentCharactersBinding
import com.ps.superheroapp.extentions.getAppComponent
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModel: CharactersViewModel

    lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentCharactersBinding.inflate(inflater, container, false).apply {
                binding = this
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent()
                .characterSubcomponent()
                .with(this)
                .build()
                .inject(this)
    }
}