package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterTrailer(private var trailerList : List<MovieVideo.MovieVideoResults>?) :
RecyclerView.Adapter<RecyclerAdapterTrailer.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_view_trailer, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trailer = trailerList?.get(position)
        if(trailer != null) {
            holder.bind(trailer)
        }
    }

    override fun getItemCount(): Int {
        var size = 0
        if(trailerList != null) {
            size = trailerList!!.size
        }
        return size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val VIDEO_BASE_URL = "https://www.youtube.com/watch?v="

        private var trailerName : TextView = itemView.findViewById(R.id.trailer_name)
        private var officialTrailer : TextView = itemView.findViewById(R.id.trailer_official)
        private var watchTrailerButton : Button = itemView.findViewById(R.id.button_trailer)

        private val off : String = "Official Trailer"
        private val unOff : String = "Unofficial Trailer"

        fun bind(m : MovieVideo.MovieVideoResults) {
            trailerName.text = m.name
            if(m.official == true) {
                officialTrailer.text = off
            }
            else {
                officialTrailer.text = unOff
            }

            watchTrailerButton.setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_BASE_URL + m.key))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("key", m.key)
                itemView.context.startActivity(intent)

                Log.d("RecyclerAdapterTrailer", "Clicked $trailerName")
            }
        }
    }
}