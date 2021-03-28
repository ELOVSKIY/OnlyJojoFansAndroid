package com.helicoptera.onlyjojofans.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.helicoptera.onlyjojofans.R
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository
import com.helicoptera.onlyjojofans.databinding.CharacterListItemBinding
import com.helicoptera.onlyjojofans.databinding.ListFragmentBinding
import com.helicoptera.onlyjojofans.ui.list.recycler.CharacterAdapter

class ListFragment : Fragment() {

    private lateinit var adapter: CharacterAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = CharacterAdapter()
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val binding: ListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters.toList())
        }
        viewModel.currentCharacter.observe(viewLifecycleOwner) { character ->
            if (character != null) {
                viewModel.onNavigateToDetail()
                CharacterRepository.currentCharacter = character
                findNavController().navigate(R.id.action_listFragment_to_detailFragment)
            }
        }
        adapter.setOnClickListener {  character ->
            viewModel.setCurrentCharacter(character)
        }
    }
}