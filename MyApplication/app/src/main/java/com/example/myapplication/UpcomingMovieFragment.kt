package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private fun getUpcomingMovie() {
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
                    val data = response.body()?.results
                    recyclerViewUpcoming.apply {
                        adapter = RecyclerAdapterUpcoming(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Toast.makeText(this@UpcomingMovieFragment.context, "Successful",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<MovieUpcoming>, t: Throwable) {
                    Toast.makeText(this@UpcomingMovieFragment.context, t.message,
                        Toast.LENGTH_SHORT).show()
                }
            })
    }
}