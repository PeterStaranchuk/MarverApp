package com.ps.superheroapp.ui.character_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ps.superheroapp.databinding.FragmentCharactersBinding
import com.ps.superheroapp.extensions.getAppComponent
import com.ps.superheroapp.ui.character_screen.list.CharacterController
import com.ps.superheroapp.ui.character_screen.list.CharacterItemDecorator
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var vm: CharactersViewModel

    @Inject
    lateinit var controller: CharacterController

    lateinit var binding: FragmentCharactersBinding

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getAppComponent()
                .characterSubcomponent()
                .with(this)
                .build()
                .inject(this)

        vm.onCharactersLoaded().observe(this, Observer {
            controller.setCharacters(it)
        })
        vm.fetchCharacters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.vm = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersList.setController(controller)
        binding.charactersList.addItemDecoration(CharacterItemDecorator())
    }
}