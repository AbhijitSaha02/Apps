package com.example.servicebookingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ApplianceRepairActivity : AppCompatActivity() {
    private lateinit var recyclerViewAppliance: RecyclerView
    val appliance: List<ServiceModel> = arrayListOf(
        ServiceModel(R.drawable.ac, "Air Conditioner"),
        ServiceModel(R.drawable.chimney, "Chimney"), ServiceModel(R.drawable.geyser, "Geyser"),
        ServiceModel(R.drawable.microwave, "Microwave"), ServiceModel(R.drawable.refrigerator, "Refrigerator"),
        ServiceModel(R.drawable.television, "Television"), ServiceModel(R.drawable.washing_machine, "Washing Machine"),
        ServiceModel(R.drawable.water_purifier, "Water Purifier"), ServiceModel(R.drawable.air_cooler, "Air Cooler")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliance_repair)

        recyclerViewAppliance = findViewById(R.id.recycle_view_cleaning)

        recyclerViewAppliance.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            adapter = RecyclerAdapterAppliance(appliance)
        }
    }
}