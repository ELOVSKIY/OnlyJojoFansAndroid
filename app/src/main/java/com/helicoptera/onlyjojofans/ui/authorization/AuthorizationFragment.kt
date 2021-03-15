package com.helicoptera.onlyjojofans.ui.authorization

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

class AuthorizationFragment : Fragment() {

    private lateinit var viewModel: AuthorizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: AuthorizationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.authorization_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AuthorizationViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onNavigationToList.observe(this) {
            if (it) {
                findNavController().navigate(R.id.action_authorizationFragment_to_listFragment)
                viewModel.onNavigateToList()
            }
        }
        viewModel.onnNavigationToRegistration.observe(this) {
            if (it) {
                findNavController().navigate(R.id.action_authorizationFragment_to_registationFragment)
                viewModel.onNavigateToRegistration()
            }
        }
    }
}