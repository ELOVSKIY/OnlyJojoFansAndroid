package com.helicoptera.onlyjojofans.ui.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.helicoptera.onlyjojofans.R

class RegistationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistationFragment()
    }

    private lateinit var viewModel: RegistationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}