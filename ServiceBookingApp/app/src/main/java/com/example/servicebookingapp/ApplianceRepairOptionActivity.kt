package com.example.servicebookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class ApplianceRepairOptionActivity : AppCompatActivity() {
    private lateinit var toolbarAppliance: Toolbar
    private lateinit var selectApplianceText: TextView
    private lateinit var imageVideoConsult: ImageView
    private lateinit var imagePersonVisit: ImageView
    private lateinit var descriptionVideoConsult: TextView
    private lateinit var descriptionPersonVisit: TextView
    private lateinit var optionPersonVisit: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliance_repair_option)
        toolbarAppliance = findViewById(R.id.appliance_repair_toolbar)
        selectApplianceText = findViewById(R.id.text_select_appliance)
        imageVideoConsult = findViewById(R.id.image_video_consult)
        imagePersonVisit = findViewById(R.id.image_person_visit)
        descriptionVideoConsult = findViewById(R.id.description_video_consult)
        descriptionPersonVisit = findViewById(R.id.description_person_visit)
        optionPersonVisit = findViewById(R.id.option_person_visit)

        val intent: Intent = intent

        when (intent.getStringExtra("appliance_name")) {
            "AC" -> {
                toolbarAppliance.title = "Air Conditioner"
                selectApplianceText.text = "AC Service \u0026 Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ac_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.appliance_repair_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Air Conditioner")
                    startActivity(intentOption)
                }
            }

            "Chimney" -> {
                toolbarAppliance.title = "Chimney"
                selectApplianceText.text = "Chimney Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.chimney_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Chimney")
                    startActivity(intentOption)
                }
            }

            "Geyser" -> {
                toolbarAppliance.title = "Geyser"
                selectApplianceText.text = "Geyser Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.geyser_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Geyser")
                    startActivity(intentOption)
                }
            }

            "Microwave" -> {
                toolbarAppliance.title = "Microwave"
                selectApplianceText.text = "Microwave Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.microwave_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Microwave")
                    startActivity(intentOption)
                }
            }

            "Refrigerator" -> {
                toolbarAppliance.title = "Refrigerator"
                selectApplianceText.text = "Refrigerator Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.refrigerator_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Refrigerator")
                    startActivity(intentOption)
                }
            }

            "TV" -> {
                toolbarAppliance.title = "Television"
                selectApplianceText.text = "Television Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.television_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Television")
                    startActivity(intentOption)
                }
            }

            "Washing Machine" -> {
                toolbarAppliance.title = "Washing Machine"
                selectApplianceText.text = "Washing Machine Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.washing_machine_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Washing Machine")
                    startActivity(intentOption)
                }
            }

            "Water Purifier" -> {
                toolbarAppliance.title = "Water Purifier"
                selectApplianceText.text = "Water Purifier Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.water_purifier_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.appliance_repair_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Water Purifier")
                    startActivity(intentOption)
                }
            }

            "Air Cooler" -> {
                toolbarAppliance.title = "Air Cooler"
                selectApplianceText.text = "Air Cooler Repair"
                imageVideoConsult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.video_consult))
                imagePersonVisit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.air_cooler_repair))
                descriptionVideoConsult.text = resources.getString(R.string.video_consult_text)
                descriptionPersonVisit.text = resources.getString(R.string.person_visit_text)
                optionPersonVisit.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Air Cooler")
                    startActivity(intentOption)
                }
            }
        }
    }
}