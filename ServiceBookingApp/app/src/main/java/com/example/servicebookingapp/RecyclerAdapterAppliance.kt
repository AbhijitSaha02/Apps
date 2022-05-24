package com.example.servicebookingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterAppliance(private var data: List<ServiceModel>) :
    RecyclerView.Adapter<RecyclerAdapterAppliance.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recycler_view,
            parent, false
        )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appliance = data[position]
        if (appliance != null) {
            holder.bind(appliance)
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
                    val intent = Intent(itemView.context, ApplianceRepairOptionActivity::class.java)

                    when (a.text) {
                        "Air Conditioner" -> intent.putExtra("appliance_name", "AC")
                        "Chimney" -> intent.putExtra("appliance_name", "Chimney")
                        "Geyser" -> intent.putExtra("appliance_name", "Geyser")
                        "Microwave" -> intent.putExtra("appliance_name", "Microwave")
                        "Refrigerator" -> intent.putExtra("appliance_name", "Refrigerator")
                        "Television" -> intent.putExtra("appliance_name", "TV")
                        "Washing Machine" -> intent.putExtra("appliance_name", "Washing Machine")
                        "Water Purifier" -> intent.putExtra("appliance_name", "Water Purifier")
                        "Air Cooler" -> intent.putExtra("appliance_name", "Air Cooler")
                    }
                    itemView.context.startActivity(intent)
                }
            }
    }
}