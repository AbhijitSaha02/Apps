package com.example.servicebookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeRepairActivity : AppCompatActivity() {
    private lateinit var recyclerViewHomeRepair: RecyclerView
    private val homeRepair: List<ServiceModel> = arrayListOf(ServiceModel(R.drawable.electrician, "Electrician"),
        ServiceModel(R.drawable.plumber, "Plumber"), ServiceModel(R.drawable.carpenter, "Carpenter"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_repair)

        recyclerViewHomeRepair = findViewById(R.id.recycle_view_home_repair)

        recyclerViewHomeRepair.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            adapter = RecyclerAdapterRepair(homeRepair)
        }
    }
}