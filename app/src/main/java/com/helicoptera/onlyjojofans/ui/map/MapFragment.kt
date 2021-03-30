package com.helicoptera.onlyjojofans.ui.map

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.helicoptera.onlyjojofans.R
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository
import com.helicoptera.onlyjojofans.databinding.MapFragmentBinding

class MapFragment : Fragment() {

    private lateinit var viewModel: MapViewModel
    private lateinit var binding: MapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync { map ->
            MapsInitializer.initialize(context)
            val characters = CharacterRepository.characters.value
            if (characters != null) {
                for (character in characters) {
                    val options = MarkerOptions()
                        .title(character.name)
                        .position(LatLng(character.lat.toDouble(), character.lon.toDouble()))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                    map.setOnMarkerClickListener { marker ->
                        val title = marker.title
                        val character = characters.find {
                            it.name == title
                        }
                        CharacterRepository.currentCharacter = character
                        findNavController().navigate(R.id.action_mapFragment_to_detailFragment)
                        return@setOnMarkerClickListener true
                    }
                    map.addMarker(options)
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
}