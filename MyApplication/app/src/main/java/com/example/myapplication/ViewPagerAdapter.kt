package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularMovieFragment()
            1 -> TopRatedMovieFragment()
            2 -> UpcomingMovieFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}