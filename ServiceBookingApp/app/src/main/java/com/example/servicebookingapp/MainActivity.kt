package com.example.servicebookingapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbar_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.homeFragment -> {
                    toolbarMain.visibility =View.GONE
                }
                R.id.bookingFragment -> {
                    toolbarMain.visibility =View.VISIBLE
                    toolbarMain.title = "Bookings"
                }
                R.id.profileFragment -> {
                    toolbarMain.visibility =View.VISIBLE
                    toolbarMain.title = "My Profile"
                }
            }
        }

        bottomNavigationView.setupWithNavController(navController)
        setSupportActionBar(toolbarMain)
    }

}