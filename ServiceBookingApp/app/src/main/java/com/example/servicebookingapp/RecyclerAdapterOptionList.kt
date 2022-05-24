package com.example.servicebookingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterOptionList(private var data: List<OptionModel>) :
    RecyclerView.Adapter<RecyclerAdapterOptionList.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recycler_view_options,
            parent, false
        )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = data[position]
        if (option != null) {
            holder.bind(option)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var nameOption: TextView
        private lateinit var ratingOption: TextView
        private lateinit var priceOption: TextView
        private lateinit var detailOption: TextView
        private lateinit var viewDetailsOption: TextView
        private lateinit var addOption: Button

        fun bind(a: OptionModel) {
            nameOption = itemView.findViewById(R.id.option_name)
            ratingOption = itemView.findViewById(R.id.option_rating)
            priceOption = itemView.findViewById(R.id.option_price)
            detailOption = itemView.findViewById(R.id.option_details)
            viewDetailsOption = itemView.findViewById(R.id.option_view_details)
            addOption = itemView.findViewById(R.id.button_add)

            nameOption.text = a.name
            ratingOption.text = a.rating.toString()
            priceOption.text = a.price
            detailOption.text = a.detail

            viewDetailsOption.setOnClickListener {

            }

            addOption.setOnClickListener {

            }
        }
    }
}