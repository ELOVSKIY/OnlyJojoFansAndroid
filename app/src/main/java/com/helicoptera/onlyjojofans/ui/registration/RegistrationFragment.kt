package com.helicoptera.onlyjojofans.ui.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.helicoptera.onlyjojofans.R
import com.helicoptera.onlyjojofans.databinding.AuthorizationFragmentBinding
import com.helicoptera.onlyjojofans.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        val binding: RegistrationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.navigateToList.observe(this.viewLifecycleOwner) {
            if (it) {
                viewModel.onNavigateToList()
                findNavController().navigate(R.id.action_registationFragment_to_listFragment)
            }
        }
    }
}