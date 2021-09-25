package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterUpcoming(private var movieData : List<MovieUpcoming.MovieUpcomingResult>?) :
    RecyclerView.Adapter<RecyclerAdapterUpcoming.ViewHolder>() {

    // Creating a View Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view,
        parent, false)
        return ViewHolder(inflate)
    }

    // Binding different views in a single item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieData?.get(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    // For returning the size of the items
    override fun getItemCount(): Int {
        var size = 0
        if(movieData != null) {
            size = movieData!!.size
        }
        return size
    }

     class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
         private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

         private var moviePoster : ImageView = itemView.findViewById(R.id.movie_poster)
         private var movieTitle : TextView = itemView.findViewById(R.id.movie_title)
         private var movieRating : TextView = itemView.findViewById(R.id.movie_rating)
         private var movieGenre : TextView = itemView.findViewById(R.id.movie_genre)
         private var movieDescription : TextView = itemView.findViewById(R.id.movie_description)
         private var moviePlay : ImageButton = itemView.findViewById(R.id.button_play_movie)

         fun bind(m: MovieUpcoming.MovieUpcomingResult) {
             Glide.with(itemView)
                 .load(IMAGE_BASE_URL + m.image)
                 .into(moviePoster)

             movieTitle.text = m.title
             movieRating.text = m.rating.toString().trim()
             movieGenre.text = m.genre.toString().trim()
             movieDescription.text = m.description
         }
    }
}