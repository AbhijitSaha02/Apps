package com.example.hbd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ShowActivity : AppCompatActivity() {

    private lateinit var imageWish : ImageView
    private lateinit var wish : TextView
    private lateinit var countdown : TextView

    private var countdownText : String? = null
    private var mDay : Int? = null
    private var mMonth : Int? = null
    private var mYear : Int? = null
    private var firstName : String? = null
    private var lastName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        imageWish = findViewById(R.id.image_wish)
        wish = findViewById(R.id.wish)
        countdown = findViewById(R.id.countdown)

        if(intent.hasExtra("day")) {
//            mDay = intent.extras?.getInt("day")
            mDay = 20
            mMonth = intent.extras?.getInt("month")
            mYear = intent.extras?.getInt("year")
            firstName = intent.extras?.getString("first_name")
            lastName = intent.extras?.getString("last_name")
        }

        if(mDay == 20 && mMonth == 9) {

            if(firstName?.lowercase().equals("sephali") && lastName?.lowercase().equals("saha")) {
                countdown.visibility = View.GONE
                imageWish.visibility = View.VISIBLE
                wish.visibility = View.VISIBLE

                imageWish.setImageResource(R.drawable.happy_birth_day_mom)
                wish.text = getString(R.string.wish_mom)
            }
        }

    }
}