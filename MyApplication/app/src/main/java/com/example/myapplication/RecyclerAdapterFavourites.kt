package com.example.myapplication

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterFavourites(private var movieData : ArrayList<FavouriteMovieData>) :
    RecyclerView.Adapter<RecyclerAdapterFavourites.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view,
            parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieData[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

        private var moviePoster : ImageView = itemView.findViewById(R.id.movie_poster)
        private var movieTitle : TextView = itemView.findViewById(R.id.movie_title)
        private var movieRating : TextView = itemView.findViewById(R.id.movie_rating)
        private var movieGenre : TextView = itemView.findViewById(R.id.movie_genre)
        private var movieDescription : TextView = itemView.findViewById(R.id.movie_description)
        private var moviePlay : ImageButton = itemView.findViewById(R.id.button_play_movie)

        fun bind(m: FavouriteMovieData) {
            Glide.with(itemView)
                .load(IMAGE_BASE_URL + m.poster_path)
                .into(moviePoster)

            movieTitle.text = m.title
            movieRating.text = m.vote_average.toString().trim()
            movieGenre.text = m.genre_ids
            movieDescription.text = m.overview

            moviePlay.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("id", m.id)
                intent.putExtra("poster_path", m.poster_path)
                intent.putExtra("title", m.title)
                intent.putExtra("original_title", m.original_title)
                intent.putExtra("original_language", m.original_language)
                intent.putExtra("release_date", m.release_date)
                intent.putExtra("vote_average", m.vote_average)
                intent.putExtra("popularity", m.popularity)
                intent.putExtra("genre_ids", m.genre_ids)
                intent.putExtra("overview", m.overview)
                intent.putExtra("video", m.video)

                intent. addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)
                Log.d("RecyclerAdapterFav", "Clicked " + m.title.toString())
            }
         }
    }

}