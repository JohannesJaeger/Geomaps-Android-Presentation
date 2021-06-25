package edu.hm.mobile_app.geomaps.integrated_maps

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.navigation.NavigationView
import com.google.maps.android.data.kml.KmlLayer
import edu.hm.mobile_app.geomaps.R


class MapsFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var googleMap: GoogleMap

    private val munichCentral = LatLng(48.1371349, 11.5754819)
    private val munichHm = LatLng(48.15516481752776, 11.555527064670908)

    private lateinit var centralCircle: Circle
    private lateinit var hmRouteLine: Polyline
    private lateinit var bavariaPolygon: Polygon
    private lateinit var germanCitiesKml: KmlLayer

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap

        // Setup layers in map
        addMarkers()
        addCircles()
        addPolylines()
        addPolygons()
        addKmls()

        // Move camera to munich central
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(munichCentral, 17F))
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
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun addMarkers() {
        // Markers

        // Add marker in munich central
        googleMap.addMarker(
            MarkerOptions()
                .position(munichCentral)
                .title("Munich Central")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(true)
                .alpha(0.6F)
        )

        // Add marker at Hochschule München
        googleMap.addMarker(
            MarkerOptions()
                .position(munichHm)
                .title("Hochschule München")
                .snippet("Lothstaße, R Building")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hm_logo))
        ).tag = "This marker is at HM Munich"

        // React to marker clicking
        googleMap.setOnMarkerClickListener { marker ->
            if(marker.isVisible) {
                val tag = if (tag == null) {
                    ""
                } else {
                    "\n" + marker.tag
                }
                Toast.makeText(
                    requireContext(),
                    "Clicked marker " + marker.title + tag,
                    Toast.LENGTH_LONG
                ).show()
            }
            false
        }
    }

    private fun addCircles() {
        // Circles

        // Add circle in munich central with a radius of 2km
        centralCircle = googleMap.addCircle(
            CircleOptions()
                .center(munichCentral)
                .radius(2000.0)
                .fillColor(Color.argb(0.5F, 1F, 0F, 0F))
                .strokeColor(Color.argb(0.8F, 0F, 0F, 1F))
                .clickable(true)
                .visible(false)
        )

        // React to circle clicking
        googleMap.setOnCircleClickListener { circle ->
            if(circle.isVisible) {
                Toast.makeText(requireContext(), "Clicked circle", Toast.LENGTH_LONG).show()
            }
            false
        }
    }

    private fun addPolylines() {
        // Polylines

        // Add polyline from central to Hochschule München
        hmRouteLine = googleMap.addPolyline(
            PolylineOptions()
                .add(
                    munichCentral,
                    LatLng(48.13945050104181, 11.565495103793761),
                    LatLng(48.14098950154444, 11.560988762892997),
                    LatLng(48.14683120848391, 11.558925253204277),
                    LatLng(48.15102224892863, 11.5579238441124),
                    LatLng(48.15429314941432, 11.553825672182985),
                    munichHm
                )
                .color(Color.argb(0.8F, 0.9F, 0F, 0.2F))
                .clickable(true)
                .visible(false)
        )

        // React to polyline clicking
        googleMap.setOnPolylineClickListener { polyline ->
            if(polyline.isVisible) {
                Toast.makeText(requireContext(), "Clicked polyline", Toast.LENGTH_LONG).show()
            }
            false
        }
    }

    private fun addPolygons() {
        // Polygon

        // Add polygon that marks bavaria
        bavariaPolygon = googleMap.addPolygon(
            PolygonOptions()
                .add(
                    LatLng(47.56757520127919, 9.754512337062113),
                    LatLng(47.497391183517536, 13.00094574278048),
                    LatLng(48.12566812531516, 12.713084652618262),
                    LatLng(48.725257913374584, 13.832544447693559),
                    LatLng(50.4308179055149, 11.889482089098577),
                    LatLng(50.557987158405375, 10.050369568617729),
                    LatLng(50.098574316326186, 9.002875046082984),
                    LatLng(49.582898199481974, 9.098828742803724),
                    LatLng(49.789828731076895, 9.754512337062113),
                    LatLng(48.68832063143262, 10.482161203861057),
                    LatLng(48.38122538956775, 10.010388861650753),
                    LatLng(47.70228051397404, 10.122334841158285),
                )
                .strokeColor(Color.RED)
                .strokeWidth(5F)
                .fillColor(Color.argb(0.5F, 0F, 0F, 1F))
                .clickable(true)
                .visible(false)
        )

        // React to polygon clicking
        googleMap.setOnPolygonClickListener { polygon ->
            if(polygon.isVisible) {
                Toast.makeText(requireContext(), "Clicked polygon", Toast.LENGTH_LONG).show()
            }
            false
        }
    }

    private fun addKmls() {
        // Load kml file with some german cities
        germanCitiesKml = KmlLayer(googleMap, R.raw.german_cities, requireContext())
    }

    // Sidebar buttons
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_maps_map_type_none -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_NONE
                true
            }
            R.id.toolbar_maps_map_type_normal -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.toolbar_maps_map_type_satellite -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.toolbar_maps_map_type_terrain -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            R.id.toolbar_maps_map_type_hybrid -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            R.id.toolbar_maps_traffic_control -> {
                googleMap.isTrafficEnabled = !googleMap.isTrafficEnabled
                true
            }
            R.id.toolbar_maps_building_control -> {
                googleMap.isBuildingsEnabled = !googleMap.isBuildingsEnabled
                true
            }
            R.id.toolbar_maps_show_hochschule_münchen -> {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(munichHm, 17F))
                true
            }
            R.id.toolbar_maps_mark_munich -> {
                if (!centralCircle.isVisible) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(munichCentral, 13F))
                }
                centralCircle.isVisible = !centralCircle.isVisible
                true
            }
            R.id.toolbar_maps_hochschule_münchen_route -> {
                if (!hmRouteLine.isVisible) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(munichCentral, 13.5F))
                }
                hmRouteLine.isVisible = !hmRouteLine.isVisible
                true
            }
            R.id.toolbar_maps_bavaria_polygon -> {
                if (!bavariaPolygon.isVisible) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(49.2458973513043, 11.204244747495697), 6F))
                }
                bavariaPolygon.isVisible = !bavariaPolygon.isVisible
                true
            }
            R.id.toolbar_maps_show_german_cities -> {
                if(!germanCitiesKml.isLayerOnMap) {
                    germanCitiesKml.addLayerToMap()
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.87575271180225, 11.062107259837989), 6.8F))
                } else {
                    germanCitiesKml.removeLayerFromMap()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}