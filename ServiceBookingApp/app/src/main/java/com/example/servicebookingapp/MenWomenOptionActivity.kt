package com.example.servicebookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MenWomenOptionActivity : AppCompatActivity() {
    private lateinit var toolbarMenWomen: Toolbar
    private lateinit var selectText: TextView
    private lateinit var imageClassic:ImageView
    private lateinit var imageRoyale: ImageView
    private lateinit var descriptionClassic: TextView
    private lateinit var descriptionRoyale: TextView
    private lateinit var optionClassic: ImageView
    private lateinit var optionRoyale: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_women_option)
        toolbarMenWomen = findViewById(R.id.men_women_toolbar)
        selectText = findViewById(R.id.text_select)
        imageClassic = findViewById(R.id.image_classic)
        imageRoyale = findViewById(R.id.image_royale)
        descriptionClassic = findViewById(R.id.description_classic)
        descriptionRoyale = findViewById(R.id.description_royale)
        optionClassic = findViewById(R.id.option_classic)
        optionRoyale = findViewById(R.id.option_royale)

        val intent: Intent = intent

        when (intent.getStringExtra("select_text")) {
            "womenSalon" -> {
                toolbarMenWomen.title = "Salon for Women"
                selectText.text = "Select your Salon"
                imageClassic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.classic_women_salon))
                imageRoyale.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.royale_women_salon))
                descriptionClassic.text = "Economical"
                descriptionRoyale.text = "Premium"
                optionClassic.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Women Salon Premium")
                    startActivity(intentOption)
                }
                optionRoyale.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Women Salon Royale")
                    startActivity(intentOption)
                }
            }

            "womenSpa" -> {
                toolbarMenWomen.title = "Spa for Women"
                selectText.text = "Select your Spa"
                imageClassic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.classic_women_spa))
                imageRoyale.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.royale_women_spa))
                descriptionClassic.text = resources.getString(R.string.classic_women_spa_text)
                descriptionRoyale.text = resources.getString(R.string.royale_women_spa_text)
                optionClassic.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Women Spa Premium")
                    startActivity(intentOption)
                }
                optionRoyale.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Women Spa Royale")
                    startActivity(intentOption)
                }
            }

            "menSalon" -> {
                toolbarMenWomen.title = "Select your Salon"
                selectText.text = "Select your Salon"
                imageClassic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.classic_men_salon))
                imageRoyale.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.royale_men_salon))
                descriptionClassic.text = resources.getString(R.string.classic_men_salon_text)
                descriptionRoyale.text = resources.getString(R.string.royale_men_salon_text)
                optionClassic.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Men Salon Premium")
                    startActivity(intentOption)
                }
                optionRoyale.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Men Salon Royale")
                    startActivity(intentOption)
                }
            }

            "menMassage" -> {
                toolbarMenWomen.title = "Massage for Men"
                selectText.text = "Select your Massage"
                imageClassic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.classic_men_massage))
                imageRoyale.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.royale_men_massage))
                descriptionClassic.text = resources.getString(R.string.classic_men_massage_text)
                descriptionRoyale.text = resources.getString(R.string.royale_men_massage_text)
                optionClassic.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Men Massage Premium")
                    startActivity(intentOption)
                }
                optionRoyale.setOnClickListener {
                    val intentOption = Intent(this, OptionsActivity::class.java)
                    intentOption.putExtra("title", "Men Massage Royale")
                    startActivity(intentOption)
                }
            }
        }

        setSupportActionBar(toolbarMenWomen)
    }
}