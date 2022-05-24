package com.example.servicebookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PestControlActivity : AppCompatActivity() {
    private lateinit var generalPestControl: ImageView
    private lateinit var bedBugsControl: ImageView
    private lateinit var termiteControl: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pest_control)

        generalPestControl = findViewById(R.id.image_general_pest)
        bedBugsControl = findViewById(R.id.image_bed_bugs)
        termiteControl = findViewById(R.id.image_termite)

        generalPestControl.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            intent.putExtra("title", "General Pest Control")
            startActivity(intent)
        }

        bedBugsControl.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            intent.putExtra("title", "Bed Bugs Control")
            startActivity(intent)
        }

        termiteControl.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            intent.putExtra("title", "Termite Control")
            startActivity(intent)
        }
    }
}