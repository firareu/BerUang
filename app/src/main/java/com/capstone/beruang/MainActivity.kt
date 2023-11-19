package com.capstone.beruang

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.beruang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*lateinit var goBarChart: Button

    lateinit var goPieChart:Button

    lateinit var goRadarChart:Button*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur toolbar sebagai ActionBar
        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_article, R.id.navigation_management, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*goBarChart=findViewById(R.id.go_bar_chart)

        goPieChart=findViewById(R.id.go_pie_chart)

        goRadarChart=findViewById(R.id.go_radar_chart)

        *//*goBarChart.setOnClickListener {

            startActivity(Intent(this,BarChartActivity::class.java))

        }*//*

        goPieChart.setOnClickListener {

            startActivity(Intent(this,PieChartActivity::class.java))

        }

        *//*goRadarChart.setOnClickListener {

            startActivity(Intent(this,RadarChartActivity::class.java))

        }*/
    }
}