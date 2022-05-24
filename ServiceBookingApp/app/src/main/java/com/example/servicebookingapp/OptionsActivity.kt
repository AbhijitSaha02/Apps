package com.example.servicebookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class OptionsActivity : AppCompatActivity() {
    private lateinit var toolbarOptions: Toolbar
    private lateinit var recyclerViewOptions: RecyclerView
    private lateinit var imageOptions: ImageView

    // Women's Salon
    private val womenSalonPremiumOption: List<OptionModel> = arrayListOf(
        OptionModel("Premium", "Hair Cut", 4.5, "600", "XXXX \n YYYY"),
        OptionModel("Premium", "Hair Treatment", 4.6, "450", "AAAA \n BBBB")
    )
    private val womenSalonRoyaleOption: List<OptionModel> = arrayListOf(
        OptionModel("Royale", "Hair Cut", 4.5, "600", "XXXX \n YYYY"),
        OptionModel("Royale", "Hair Treatment", 4.6, "450", "AAAA \n BBBB")
    )

    // Women's Spa
    private val womenSpaPremiumOption: List<OptionModel> = arrayListOf(
        OptionModel("Premium", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Premium", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )
    private val womenSpaRoyaleOption: List<OptionModel> = arrayListOf(
        OptionModel("Royale", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Royale", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Men's Salon
    private val menSalonPremiumOption: List<OptionModel> = arrayListOf(
        OptionModel("Premium", "Hair Cut", 4.5, "600", "XXXX \n YYYY"),
        OptionModel("Premium", "Hair Treatment", 4.6, "450", "AAAA \n BBBB")
    )
    private val menSalonRoyaleOption: List<OptionModel> = arrayListOf(
        OptionModel("Royale", "Hair Cut", 4.5, "600", "XXXX \n YYYY"),
        OptionModel("Royale", "Hair Treatment", 4.6, "450", "AAAA \n BBBB")
    )

    // Men Massage
    private val menMassagePremiumOption: List<OptionModel> = arrayListOf(
        OptionModel("Premium", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Premium", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )
    private val menMassageRoyaleOption: List<OptionModel> = arrayListOf(
        OptionModel("Royale", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Royale", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Air Conditioner
    private val acOption: List<OptionModel> = arrayListOf(
        OptionModel("Air Conditioner", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Air Conditioner", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Chimney
    private val chimneyOption: List<OptionModel> = arrayListOf(
        OptionModel("Chimney", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Chimney", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Geyser
    private val geyserOption: List<OptionModel> = arrayListOf(
        OptionModel("Geyser", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Geyser", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Microwave
    private val microwaveOption: List<OptionModel> = arrayListOf(
        OptionModel("Microwave", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Microwave", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Refrigerator
    private val refrigeratorOption: List<OptionModel> = arrayListOf(
        OptionModel("Refrigerator", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Refrigerator", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Television
    private val tvOption: List<OptionModel> = arrayListOf(
        OptionModel("Television", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Television", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Washing Machine
    private val washingMachineOption: List<OptionModel> = arrayListOf(
        OptionModel("Washing Machine", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Washing Machine", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Water Purifier
    private val waterPurifierOption: List<OptionModel> = arrayListOf(
        OptionModel("Water Purifier", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Water Purifier", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Air Cooler
    private val airCoolerOption: List<OptionModel> = arrayListOf(
        OptionModel("Air Cooler", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Air Cooler", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Bathroom and Kitchen Cleaning
    private val bathroomKitchenOption: List<OptionModel> = arrayListOf(
        OptionModel("Bathroom & Kitchen Cleaning", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Bathroom & Kitchen Cleaning", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Full Home Cleaning
    private val fullHomeOption: List<OptionModel> = arrayListOf(
        OptionModel("Full Home Cleaning", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Full Home Cleaning", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Sofa and Carpet Cleaning
    private val sofaCarpetOption: List<OptionModel> = arrayListOf(
        OptionModel("Sofa & Carpet Cleaning", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Sofa & Carpet Cleaning", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // General Pest Control
    private val generalPestOption: List<OptionModel> = arrayListOf(
        OptionModel("Cockroach, Ant & General Pest Control", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Cockroach, Ant & General Pest Control", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Bed Bugs Control
    private val bedBugsOption: List<OptionModel> = arrayListOf(
        OptionModel("Bed Bugs Control", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Bed Bugs Control", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Termite Control
    private val termiteOption: List<OptionModel> = arrayListOf(
        OptionModel("Termite Control", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Termite Control", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Car Cleaning
    private val carCleaningOption: List<OptionModel> = arrayListOf(
        OptionModel("Car Cleaning", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Car Cleaning", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Disinfection Services
    private val disinfectionOption: List<OptionModel> = arrayListOf(
        OptionModel("Disinfection Services", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Disinfection Services", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Electrician
    private val electricianOption: List<OptionModel> = arrayListOf(
        OptionModel("Electrician", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Electrician", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Plumber
    private val plumberOption: List<OptionModel> = arrayListOf(
        OptionModel("Plumber", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Plumber", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Carpenter
    private val carpenterOption: List<OptionModel> = arrayListOf(
        OptionModel("Carpenter", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Carpenter", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    // Home Painting
    private val homePaintingOption: List<OptionModel> = arrayListOf(
        OptionModel("Home Painting", "Geiuhss", 4.2, "500", "XXXX \n YYYY"),
        OptionModel("Home Painting", "sugsiu", 4.3, "720", "AAAA \n BBBB")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        toolbarOptions = findViewById(R.id.toolbar_options)
        recyclerViewOptions = findViewById(R.id.recycler_view_options)
        imageOptions = findViewById(R.id.app_bar_image)

        val intent: Intent = intent

        toolbarOptions.title = intent.getStringExtra("title")
        setSupportActionBar(toolbarOptions)

        recyclerViewOptions.apply {
            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            when(intent.getStringExtra("title")) {
                "Women Salon Premium" -> adapter = RecyclerAdapterOptionList(womenSalonPremiumOption)
                "Women Salon Royale" -> adapter = RecyclerAdapterOptionList(womenSalonRoyaleOption)
                "Women Spa Premium" -> adapter = RecyclerAdapterOptionList(womenSpaPremiumOption)
                "Women Spa Royale" -> adapter = RecyclerAdapterOptionList(womenSpaRoyaleOption)
                "Men Salon Premium" -> adapter = RecyclerAdapterOptionList(menSalonPremiumOption)
                "Men Salon Royale" -> adapter = RecyclerAdapterOptionList(menSalonRoyaleOption)
                "Men Massage Premium" -> adapter = RecyclerAdapterOptionList(menMassagePremiumOption)
                "Men Massage Royale" -> adapter = RecyclerAdapterOptionList(menMassageRoyaleOption)
                "Air Conditioner" -> adapter = RecyclerAdapterOptionList(acOption)
                "Chimney" -> adapter = RecyclerAdapterOptionList(chimneyOption)
                "Geyser" -> adapter = RecyclerAdapterOptionList(geyserOption)
                "Microwave" -> adapter = RecyclerAdapterOptionList(microwaveOption)
                "Refrigerator" -> adapter = RecyclerAdapterOptionList(refrigeratorOption)
                "Television" -> adapter = RecyclerAdapterOptionList(tvOption)
                "Washing Machine" -> adapter = RecyclerAdapterOptionList(washingMachineOption)
                "Water Purifier" -> adapter = RecyclerAdapterOptionList(waterPurifierOption)
                "Air Cooler" -> adapter = RecyclerAdapterOptionList(airCoolerOption)
                "Bathroom and Kitchen Cleaning" -> adapter = RecyclerAdapterOptionList(bathroomKitchenOption)
                "Full Home Cleaning" -> adapter = RecyclerAdapterOptionList(fullHomeOption)
                "Sofa and Carpet Cleaning" -> adapter = RecyclerAdapterOptionList(sofaCarpetOption)
                "General Pest Control" -> adapter = RecyclerAdapterOptionList(generalPestOption)
                "Bed Bugs Control" -> adapter = RecyclerAdapterOptionList(bedBugsOption)
                "Termite Control" -> adapter = RecyclerAdapterOptionList(termiteOption)
                "Car Cleaning" -> adapter = RecyclerAdapterOptionList(carCleaningOption)
                "Disinfection Services" -> adapter = RecyclerAdapterOptionList(disinfectionOption)
                "Electrician" -> adapter = RecyclerAdapterOptionList(electricianOption)
                "Plumber" -> adapter = RecyclerAdapterOptionList(plumberOption)
                "Carpenter" -> adapter = RecyclerAdapterOptionList(carpenterOption)
                "Home Painting" -> adapter = RecyclerAdapterOptionList(homePaintingOption)
            }
        }

    }
}