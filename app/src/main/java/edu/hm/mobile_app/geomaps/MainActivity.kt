package edu.hm.mobile_app.geomaps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.munich_streetview_button).setOnClickListener {
            val uri = Uri.parse("google.streetview:cbll=48.1371349,11.5754819")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.munich_map_button).setOnClickListener {
            val uri = Uri.parse("geo:48.1371349,11.5754819?z=18")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.hochschule_münchen_map_button).setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=Hochschule+München+Lotstraße")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.hochschule_münchen_public_transport_map_button).setOnClickListener {
            val uri = Uri.parse("geo:48.1538893,11.5544265?z=16&q=public+transport")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.munich_restaurant_map_button).setOnClickListener {
            val uri = Uri.parse("geo:48.1371349,11.5754819?z=15&q=restaurant")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.basic_route_button).setOnClickListener {
            val uri = Uri.parse("google.navigation:q=Hochschule+München+Lotstraße&mode=b")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        findViewById<Button>(R.id.advanced_route_button).setOnClickListener {
            val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&dir_action=navigate&origin=48.1680391,11.7178413&destination=48.1546997,11.5567071&waypoints=48.135,11.581|48.1888204,11.6408373&travelmode=walking")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
}