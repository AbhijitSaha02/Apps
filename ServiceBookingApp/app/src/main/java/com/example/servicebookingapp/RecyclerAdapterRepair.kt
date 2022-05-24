package com.example.servicebookingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterRepair(private var data: List<ServiceModel>) :
    RecyclerView.Adapter<RecyclerAdapterRepair.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recycler_view,
            parent, false
        )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repair = data[position]
        if (repair != null) {
            holder.bind(repair)
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
                    "Electrician" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", "Electrician")
                        itemView.context.startActivity(intent)
                    }

                    "Plumber" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", "Plumber")
                        itemView.context.startActivity(intent)
                    }

                    "Carpenter" -> {
                        val intent = Intent(itemView.context, OptionsActivity::class.java)
                        intent.putExtra("title", "Carpenter")
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }
}