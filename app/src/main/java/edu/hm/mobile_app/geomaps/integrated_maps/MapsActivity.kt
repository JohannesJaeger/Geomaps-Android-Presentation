package edu.hm.mobile_app.geomaps.integrated_maps

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import edu.hm.mobile_app.geomaps.R


class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_maps)
        setSupportActionBar(toolbar)

        val mapsFragment = MapsFragment()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(mapsFragment)

        supportFragmentManager.beginTransaction().replace(R.id.maps_frame_layout, mapsFragment).commit()
    }
}