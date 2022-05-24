package com.example.servicebookingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterCleaning(private var data: List<ServiceModel>) :
    RecyclerView.Adapter<RecyclerAdapterCleaning.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recycler_view,
            parent, false
        )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cleaning = data[position]
        if (cleaning != null) {
            holder.bind(cleaning)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var image: ImageView
        private lateinit var text: TextView
        private lateinit var option: ImageView

        fun bind(a: ServiceModel) {
            image = itemView.findViewById(R.id.image)
            text = itemView.findViewById(R.id.text)
            option = itemView.findViewById(R.id.option)

            Glide.with(itemView)
                .load(a.image)
                .into(image)

            text.text = a.text

            option.setOnClickListener {
                when (a.text) {
                    "Bathroom and Kitchen Cleaning" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", a.text)
                        itemView.context.startActivity(intent)
                    }

                    "Full Home Cleaning" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", a.text)
                        itemView.context.startActivity(intent)
                    }

                    "Sofa and Carpet Cleaning" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", a.text)
                        itemView.context.startActivity(intent)
                    }

                    "Pest Control" -> {
                        val intent = Intent(itemView.context, PestControlActivity::class.java)
                        itemView.context.startActivity(intent)
                    }

                    "Car Cleaning" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", a.text)
                        itemView.context.startActivity(intent)
                    }

                    "Disinfection Services" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", a.text)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }
}