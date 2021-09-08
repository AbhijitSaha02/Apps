package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LatestMovieFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_movie, container, false)
    }

    // onViewCreated will execute the layout gets inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recylerView_latestMovies)

        // apply adds all the properties of our RecyclerView in a single block
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Initializing the adapter of RecyclerView
            adapter = RecyclerAdapter(nameList)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(this.context,
                    DividerItemDecoration.VERTICAL)
            )
            setHasFixedSize(true)
        }
    }
}