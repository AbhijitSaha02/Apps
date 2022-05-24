package com.example.servicebookingapp

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var resetButton : TextView
    private lateinit var email : EditText
    private lateinit var progressBarReset : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        resetButton = findViewById(R.id.button_reset)
        email = findViewById(R.id.email_forgot_password)
        progressBarReset = findViewById(R.id.progressBar_forgot_password)

        resetButton.setOnClickListener {

            val confirmEmail = email.text.toString().trim()

            if(confirmEmail.isEmpty()) {
                email.error = "E-mail Required"
                email.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(confirmEmail).matches()) {
                email.error = "Valid E-mail Required"
                email.requestFocus()
                return@setOnClickListener
            }

            progressBarReset.visibility = View.VISIBLE

            FirebaseAuth.getInstance().sendPasswordResetEmail(confirmEmail)
                .addOnCompleteListener { task ->
                    progressBarReset.visibility = View.INVISIBLE

                    if(task.isSuccessful) {
                        Log.d("Forgot Password", "Successful")
                    }
                    else {
                        Log.e("Forgot Password", task.exception?.message.toString())
                    }
                }
        }

    }
}