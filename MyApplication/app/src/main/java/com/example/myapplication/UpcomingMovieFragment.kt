package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingMovieFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit: Retrofit

    private lateinit var recyclerViewUpcoming: RecyclerView

    private var data : ArrayList<MovieUpcoming.MovieUpcomingResult>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movie, container, false)
    }

    // onViewCreated will execute the layout gets inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUpcoming = view.findViewById(R.id.recyclerView_upcomingMovies)
        setHasOptionsMenu(true)

        // apply adds all the properties of our RecyclerView in a single block
        recyclerViewUpcoming.apply {
            layoutManager = LinearLayoutManager(this.context)

            adapter = RecyclerAdapterUpcoming(null)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            getUpcomingMovie()
        }
    }

    fun getUpcomingMovie() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiInterface = retrofit.create(MovieApiInterface::class.java)

        movieApiInterface.getUpcomingMovieList(1)
            .enqueue(object : Callback<MovieUpcoming> {
                override fun onResponse(
                    call: Call<MovieUpcoming>,
                    response: Response<MovieUpcoming>
                ) {
                    data = response.body()?.results

                    recyclerViewUpcoming.apply {
                        adapter = RecyclerAdapterUpcoming(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Log.d("Upcoming Movie Frag", "Successful")
                }

                override fun onFailure(call: Call<MovieUpcoming>, t: Throwable) {
                    Log.e("Upcoming Movie Frag", t.message.toString())
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        inflater.inflate(R.menu.toolbar_menu_upcoming, menu)

        val search : MenuItem? = menu.findItem(R.id.app_bar_search_upcoming)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != "") {

                    val newData = data?.filter { movieUpcomingResults ->
                        val s = (movieUpcomingResults.title)?.lowercase()
                        newText!!.lowercase().let {
                            s!!.contains(it, ignoreCase = true)
                        }
                    }
                    recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(newData)
                    recyclerViewUpcoming.adapter?.notifyDataSetChanged()
                }
                else {
                    recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(data)
                    recyclerViewUpcoming.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_alphabet_asc_upcoming -> {
                val sortedData = data?.sortedBy { movieUpcomingResults ->
                    movieUpcomingResults.title
                }
                recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(sortedData)
                recyclerViewUpcoming.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_alphabet_desc_upcoming -> {
                val sortedData = data?.sortedByDescending { movieUpcomingResults ->
                    movieUpcomingResults.title
                }
                recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(sortedData)
                recyclerViewUpcoming.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_rating_upcoming -> {
                val sortedData = data?.sortedByDescending { movieUpcomingResults ->
                    movieUpcomingResults.rating
                }
                recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(sortedData)
                recyclerViewUpcoming.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_popularity_upcoming -> {
                val sortedData = data?.sortedByDescending { movieUpcomingResults ->
                    movieUpcomingResults.popularity
                }
                recyclerViewUpcoming.adapter = RecyclerAdapterUpcoming(sortedData)
                recyclerViewUpcoming.adapter!!.notifyDataSetChanged()
            }
        }

        return false
    }
}