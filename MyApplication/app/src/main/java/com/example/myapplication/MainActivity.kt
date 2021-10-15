package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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

    val trm : TopRatedMovieFragment? = null
    val um : UpcomingMovieFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController : NavController = navHostFragment.navController

        if(currentUser != null) {
            bottomNavigationView.menu.findItem(R.id.profileFragment).isVisible = true
            bottomNavigationView.menu.findItem(R.id.loginFragment).isVisible = false
        }

        bottomNavigationView.setupWithNavController(navController)

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.sort_by_alphabet_asc -> {
//                trm?.getTopRatedMovie(1)
//                um?.getUpcomingMovie(1)
//                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}