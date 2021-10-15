package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TopRatedMovieFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit : Retrofit

    private lateinit var recyclerViewTopRated : RecyclerView

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

            getTopRatedMovie(null)
        }
    }

    fun getTopRatedMovie(menuId : Int?) {
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
                    val data = response.body()?.results

                    when(menuId) {
                        1 -> Collections.sort(data)
                    }

                    recyclerViewTopRated.apply {
                        adapter = RecyclerAdapterTopRated(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Toast.makeText(this@TopRatedMovieFragment.context, "Successful",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<MovieTopRated>, t: Throwable) {
                    Toast.makeText(this@TopRatedMovieFragment.context, t.message,
                        Toast.LENGTH_SHORT).show()
                }

            })
    }
}