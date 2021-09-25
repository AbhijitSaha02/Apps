package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LatestMovieFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit : Retrofit

    private lateinit var recyclerViewLatest : RecyclerView

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

        recyclerViewLatest = view.findViewById(R.id.recylcerView_latestMovies)

        // apply adds all the properties of our RecyclerView in a single block
        recyclerViewLatest.apply {
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

        movieApiInterface.getLatestMovieList()
            .enqueue(object : Callback<MovieLatest>{
                override fun onResponse(
                    call: Call<MovieLatest>,
                    response: Response<MovieLatest>)
                {
                    val data : List<MovieLatest?> = listOf(response.body())
                    recyclerViewLatest.apply {
                        adapter = RecyclerAdapterLatest(data)
                        adapter?.notifyDataSetChanged()
                    }
                    Toast.makeText(this@LatestMovieFragment.context, "Successful",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<MovieLatest>, t: Throwable) {
                    Toast.makeText(this@LatestMovieFragment.context, t.message,
                        Toast.LENGTH_SHORT).show()
                }

            })
    }
}