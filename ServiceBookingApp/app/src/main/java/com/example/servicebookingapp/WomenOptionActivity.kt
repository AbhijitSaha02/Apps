package com.example.servicebookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WomenOptionActivity : AppCompatActivity() {
    private lateinit var womenSalon: ImageView
    private lateinit var womenSpa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_women_option)
        womenSalon = findViewById(R.id.option_women_salon)
        womenSpa = findViewById(R.id.option_women_spa)

        womenSalon.setOnClickListener {
            val intent = Intent(this, MenWomenOptionActivity::class.java)
            intent.putExtra("select_text", "womenSalon")
            startActivity(intent)
        }

        womenSpa.setOnClickListener {
            val intent = Intent(this, MenWomenOptionActivity::class.java)
            intent.putExtra("select_text", "womenSpa")
            startActivity(intent)
        }
    }
}