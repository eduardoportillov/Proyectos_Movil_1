package com.moviles.marketplace.ui.activities.chat

import android.app.Activity
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.moviles.marketplace.R
import com.moviles.marketplace.ui.fragments.MapsFragment

class MapsMenssageFragment : Fragment() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    private val callback = OnMapReadyCallback { googleMap ->
        val locationMsg = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(locationMsg).title("Marker in locationMsg"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_maps_menssage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}

