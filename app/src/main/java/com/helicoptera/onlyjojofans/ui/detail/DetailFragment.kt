package com.helicoptera.onlyjojofans.ui.detail

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
import com.helicoptera.onlyjojofans.databinding.DetailFragmentBinding
import com.helicoptera.onlyjojofans.databinding.RegistrationFragmentBinding
import com.helicoptera.onlyjojofans.ui.registration.recycler.ImageAdapter

class DetailFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val binding: DetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        val character = CharacterRepository.currentCharacter
        binding.character = character
        binding.viewModel = viewModel
        imageAdapter = ImageAdapter()
        binding.recycler.adapter = imageAdapter
        imageAdapter.submitList(character?.images)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        viewModel.navigateToEdit.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.onNavigateToEdit()
                findNavController().navigate(R.id.action_detailFragment_to_editFragment)
            }
        }
    }
}