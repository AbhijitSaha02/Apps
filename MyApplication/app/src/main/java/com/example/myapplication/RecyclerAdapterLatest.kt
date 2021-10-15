package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterLatest(private var movieData: List<MovieLatest?>) :
    RecyclerView.Adapter<RecyclerAdapterLatest.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view,
        parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieData[position]
        if(movie != null) {
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        var size = 0
        size = movieData.size
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

        fun bind(m: MovieLatest) {
            val g : MovieLatest.LatestGenres? = null

            Glide.with(itemView)
                .load(IMAGE_BASE_URL + m.image)
                .into(moviePoster)

            movieTitle.text = m.title
            movieRating.text = m.rating.toString().trim()
            movieGenre.text = g?.genreId.toString().trim()
            movieDescription.text = m.description

            moviePlay.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("id", m.id)
                intent.putExtra("poster_path", m.image)
                intent.putExtra("title", m.title)
                intent.putExtra("original_title", m.originalTitle)
                intent.putExtra("original_language", m.originalLanguage)
                intent.putExtra("release_date", m.releaseDate)
                intent.putExtra("vote_average", m.rating)
                intent.putExtra("popularity", m.popularity)
                intent.putExtra("genreName", g?.genreName)
                intent.putExtra("overview", m.description)
                intent.putExtra("video", m.video)

                intent. addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)
                Toast.makeText(itemView.context, "You clicked " + m.title.toString(),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}