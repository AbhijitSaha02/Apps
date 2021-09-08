package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController : NavController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.loginFragment,
//            R.id.profileFragment, R.id.extraFragment))

        // setupActionBarWithNavController(navController, appBarConfiguration)

        if(currentUser != null) {
            bottomNavigationView.menu.findItem(R.id.profileFragment).isVisible = true
            bottomNavigationView.menu.findItem(R.id.loginFragment).isVisible = false
        }

        bottomNavigationView.setupWithNavController(navController)

    }
}