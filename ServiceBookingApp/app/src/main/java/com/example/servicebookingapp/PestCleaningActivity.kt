package com.example.servicebookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PestCleaningActivity : AppCompatActivity() {
    private lateinit var recyclerViewCleaning: RecyclerView
    val pestCleaning: List<ServiceModel> = arrayListOf(ServiceModel(R.drawable.bathroom_cleaning, "Bathroom and Kitchen Cleaning"),
        ServiceModel(R.drawable.home_cleaning, "Full Home Cleaning"), ServiceModel(R.drawable.sofa_cleaning, "Sofa and Carpet Cleaning"),
        ServiceModel(R.drawable.pest_control, "Pest Control"), ServiceModel(R.drawable.car_cleaning, "Car Cleaning"),
        ServiceModel(R.drawable.disinfection, "Disinfection Services"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pest_cleaning)

        recyclerViewCleaning = findViewById(R.id.recycle_view_cleaning)

        recyclerViewCleaning.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            adapter = RecyclerAdapterCleaning(pestCleaning)
        }
    }
}