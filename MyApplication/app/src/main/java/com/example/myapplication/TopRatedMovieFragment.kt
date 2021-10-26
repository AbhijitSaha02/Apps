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

class TopRatedMovieFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit : Retrofit

    private lateinit var recyclerViewTopRated : RecyclerView

    private var data : ArrayList<MovieTopRated.MovieTopRatedResults>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_movie, container, false)
    }

    // onViewCreated will execute the layout gets inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewTopRated = view.findViewById(R.id.recyclerView_topRatedMovies)
        setHasOptionsMenu(true)

        // apply adds all the properties of our RecyclerView in a single block
        recyclerViewTopRated.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            getTopRatedMovie()
        }

    }

    private fun getTopRatedMovie() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiInterface = retrofit.create(MovieApiInterface::class.java)

        movieApiInterface.getTopRatedMovieList(1)
            .enqueue(object : Callback<MovieTopRated> {
                override fun onResponse(
                    call: Call<MovieTopRated>,
                    response: Response<MovieTopRated>
                ) {
                    data = response.body()?.results

                    recyclerViewTopRated.apply {
                        adapter = RecyclerAdapterTopRated(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Log.d("Top Rated Movie Frag", "Successful")
                }

                override fun onFailure(call: Call<MovieTopRated>, t: Throwable) {
                    Log.e("Top Rated Movie Frag", t.message.toString())
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        inflater.inflate(R.menu.toolbar_menu_top_rated, menu)

        val search : MenuItem? = menu.findItem(R.id.app_bar_search_top_rated)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != "") {

                    val newData = data?.filter { movieTopRatedResults ->
                        val s = (movieTopRatedResults.title)?.lowercase()
                        newText!!.lowercase().let {
                            s!!.contains(it, ignoreCase = true)
                        }
                    }
                    recyclerViewTopRated.adapter = RecyclerAdapterTopRated(newData)
                    recyclerViewTopRated.adapter?.notifyDataSetChanged()
                }
                else {
                    recyclerViewTopRated.adapter = RecyclerAdapterTopRated(data)
                    recyclerViewTopRated.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_alphabet_asc_top_rated -> {
                val sortedData = data?.sortedBy { movieTopRatedResults ->
                    movieTopRatedResults.title
                }
                recyclerViewTopRated.adapter = RecyclerAdapterTopRated(sortedData)
                recyclerViewTopRated.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_alphabet_desc_top_rated -> {
                val sortedData = data?.sortedByDescending { movieTopRatedResults ->
                    movieTopRatedResults.title
                }
                recyclerViewTopRated.adapter = RecyclerAdapterTopRated(sortedData)
                recyclerViewTopRated.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_popularity_top_rated -> {
                val sortedData = data?.sortedByDescending { movieTopRatedResults ->
                    movieTopRatedResults.popularity
                }
                recyclerViewTopRated.adapter = RecyclerAdapterTopRated(sortedData)
                recyclerViewTopRated.adapter!!.notifyDataSetChanged()
            }
        }

        return false
    }
}