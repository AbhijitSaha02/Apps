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


class PopularMovieFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit : Retrofit

    private lateinit var recyclerViewPopular : RecyclerView

    private var data : ArrayList<MoviePopular.MoviePopularResults>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    // onViewCreated will execute the layout gets inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewPopular = view.findViewById(R.id.recylcerView_popularMovies)
        setHasOptionsMenu(true)

        // apply adds all the properties of our RecyclerView in a single block
        recyclerViewPopular.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            getLatestMovie()
        }
    }

    private fun getLatestMovie(){
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiInterface = retrofit.create(MovieApiInterface::class.java)

        movieApiInterface.getLatestMovieList(1)
            .enqueue(object : Callback<MoviePopular>{
                override fun onResponse(
                    call: Call<MoviePopular>,
                    response: Response<MoviePopular>)
                {
                    data = response.body()?.results
                    recyclerViewPopular.apply {
                        adapter = RecyclerAdapterPopular(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Log.d("Popular Movie Fragment", "Callback response successful")
                }

                override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                    Log.e("Popular Movie Fragment", t.message.toString())
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        inflater.inflate(R.menu.toolbar_menu_popular, menu)

        val search : MenuItem? = menu.findItem(R.id.app_bar_search_popular)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != "") {

                    val newData = data?.filter { moviePopularResults ->
                        val s = (moviePopularResults.title)?.lowercase()
                        newText!!.lowercase().let {
                            s!!.contains(it, ignoreCase = true)
                        }
                    }
                    recyclerViewPopular.adapter = RecyclerAdapterPopular(newData)
                    recyclerViewPopular.adapter?.notifyDataSetChanged()
                }
                else {
                    recyclerViewPopular.adapter = RecyclerAdapterPopular(data)
                    recyclerViewPopular.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_alphabet_asc_popular -> {
                val sortedData = data?.sortedBy { moviePopularResults ->
                    moviePopularResults.title
                }
                recyclerViewPopular.adapter = RecyclerAdapterPopular(sortedData)
                recyclerViewPopular.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_alphabet_desc_popular -> {
                val sortedData = data?.sortedByDescending { moviePopularResults ->
                    moviePopularResults.title
                }
                recyclerViewPopular.adapter = RecyclerAdapterPopular(sortedData)
                recyclerViewPopular.adapter!!.notifyDataSetChanged()
            }

            R.id.sort_rating_popular -> {
                val sortedData = data?.sortedByDescending { moviePopularResults ->
                    moviePopularResults.rating
                }
                recyclerViewPopular.adapter = RecyclerAdapterPopular(sortedData)
                recyclerViewPopular.adapter!!.notifyDataSetChanged()
            }
        }

        return false
    }
}