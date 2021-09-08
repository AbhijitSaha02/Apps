package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    // Overriding onCreateView function of Fragment class
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)

        tabLayout = inflate.findViewById(R.id.tabLayout)
        viewPager = inflate.findViewById(R.id.viewPager)

        // Returning the layout inflater
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Latest"
                1 -> tab.text = "Top-Rated"
                2 -> tab.text = "Upcoming"
            }
        }.attach()
    }
}