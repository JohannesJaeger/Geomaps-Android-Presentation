package edu.hm.mobile_app.geomaps.integrated_maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import edu.hm.mobile_app.geomaps.R

class MapsFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private val callback = OnMapReadyCallback { googleMap ->
        val munichCentral = LatLng(48.1371349, 11.5754819)
        googleMap.addMarker(MarkerOptions().position(munichCentral).title("Marker in Sydney"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(munichCentral, 18F))
        googleMap.isTrafficEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println("CLICKED")

        return true
    }

}