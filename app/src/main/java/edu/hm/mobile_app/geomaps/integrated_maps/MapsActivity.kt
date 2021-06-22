package edu.hm.mobile_app.geomaps.integrated_maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import edu.hm.mobile_app.geomaps.R

class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportFragmentManager.beginTransaction().replace(R.id.maps_frame_layout, MapsFragment()).commit()
    }
}