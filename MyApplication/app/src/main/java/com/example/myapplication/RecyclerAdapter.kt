package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // Creating a View Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view,
        parent, false)
        return ViewHolder(inflate)
    }

    // Binding different views in a single item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    // For returning the size of the items
    override fun getItemCount(): Int {

    }

     class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
         private var moviePoster : ImageView = itemView.findViewById(R.id.movie_poster)
         private var movieTitle : TextView = itemView.findViewById(R.id.movie_title)
         private var movieRating : TextView = itemView.findViewById(R.id.movie_rating)
         private var movieGenre : TextView = itemView.findViewById(R.id.movie_genre)
         private var movieDescription : TextView = itemView.findViewById(R.id.movie_description)
         private var moviePlay : ImageButton = itemView.findViewById(R.id.button_play_movie)

         fun bind() {

         }
    }
}