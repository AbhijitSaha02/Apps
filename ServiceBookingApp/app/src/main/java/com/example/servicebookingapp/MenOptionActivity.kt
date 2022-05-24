package com.example.servicebookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MenOptionActivity : AppCompatActivity() {
    private lateinit var menSalon: ImageView
    private lateinit var menMassage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_option)
        menSalon = findViewById(R.id.option_men_salon)
        menMassage = findViewById(R.id.option_men_massage)

        menSalon.setOnClickListener {
            val intent = Intent(this, MenWomenOptionActivity::class.java)
            intent.putExtra("select_text", "menSalon")
            startActivity(intent)
        }

        menMassage.setOnClickListener {
            val intent = Intent(this, MenWomenOptionActivity::class.java)
            intent.putExtra("select_text", "menMassage")
            startActivity(intent)
        }
    }
}