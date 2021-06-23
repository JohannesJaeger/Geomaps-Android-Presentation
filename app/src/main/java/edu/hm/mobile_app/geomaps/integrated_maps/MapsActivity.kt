package edu.hm.mobile_app.geomaps.integrated_maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import edu.hm.mobile_app.geomaps.R

class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_maps)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().replace(R.id.maps_frame_layout, MapsFragment()).commit()
    }
}