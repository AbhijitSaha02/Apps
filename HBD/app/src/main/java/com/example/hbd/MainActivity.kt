package com.example.hbd

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var firstName : TextInputEditText
    private lateinit var lastName : TextInputEditText
    private lateinit var dob : TextInputEditText
    private lateinit var calendar : ImageView
    private lateinit var nextButton : Button

    private var mDay : Int? = null
    private var mMonth : Int? = null
    private var mYear : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.first_name)
        lastName = findViewById(R.id.last_name)
        dob = findViewById(R.id.dob)
        calendar = findViewById(R.id.date_picker)
        nextButton = findViewById(R.id.button_next)

        calendar.setOnClickListener {

            val cal : Calendar = Calendar.getInstance()

            mDay = cal.get(Calendar.DAY_OF_MONTH)
            mMonth = cal.get(Calendar.MONTH)
            mYear = cal.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                dob.text = getString(R.string.date_of_birth, day, month, year).toEditable()
            }, mYear!!, mMonth!!, mDay!!)
            datePickerDialog.show()
        }


        nextButton.setOnClickListener {
            showWish()
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun showWish() {

        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("day", mDay)
        intent.putExtra("month", mMonth)
        intent.putExtra("year", mYear)
        intent.putExtra("first_name", firstName.text.toString().trim())
        intent.putExtra("last_name", lastName.text.toString().trim())

        startActivity(intent)

    }
}