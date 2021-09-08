package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit

class PhoneAuthentication : AppCompatActivity() {
    private lateinit var phoneLayout : LinearLayout
    private lateinit var verifyLayout: LinearLayout
    private lateinit var sendCode : TextView
    private lateinit var verifyCode : TextView
    private lateinit var number : TextInputEditText
    private lateinit var ccp : CountryCodePicker
    private lateinit var verificationCodeNumber : TextInputEditText
    private var verificationId : String? = null
    private lateinit var progressBar1 : ProgressBar
    private lateinit var progressBar2 : ProgressBar
    // Creating a firebase object
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_authentication)

        // Instantiating the firebase object
        mAuth = FirebaseAuth.getInstance()

        // finding the views
        phoneLayout = findViewById(R.id.phone_layout)
        verifyLayout = findViewById(R.id.verify_Layout)
        sendCode = findViewById(R.id.sendCode)
        verifyCode = findViewById(R.id.verifyCode)
        number = findViewById(R.id.phoneNumber)
        ccp = findViewById(R.id.ccp)
        verificationCodeNumber = findViewById(R.id.verificationCode)

        // Setting the LinearLayout visibilities
        phoneLayout.visibility = View.VISIBLE
        verifyLayout.visibility = View.GONE

        // What to do when the button is clicked
        sendCode.setOnClickListener {
            // Finding and setting the visibility of progressbar
            progressBar1 = findViewById(R.id.progressBarPhone1)
            progressBar1.visibility = View.VISIBLE

            val phone = number.text.toString().trim()

            // Checking if the phone number is empty or if its length is not equal to 10
            if(phone.isEmpty() || phone.length != 10) {
                number.error = "Enter a valid Phone Number"
                number.requestFocus()
                return@setOnClickListener
            }

            // Concatenating the  country code with the phone number
            val phoneNumber : String= "+" + ccp.selectedCountryCode + phone

            val options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBack)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)

            // Setting the  visibilities of the Linear Layout
            phoneLayout.visibility = View.GONE
            verifyLayout.visibility = View.VISIBLE

            }

        // When the user clicks the Verify TextView
        verifyCode.setOnClickListener {
            // Finding and setting the visibility of progressbar
            progressBar2 = findViewById(R.id.progressBarPhone2)
            progressBar2.visibility = View.VISIBLE

            val code = verificationCodeNumber.text.toString().trim()

            if(code.isEmpty()) {
                verificationCodeNumber.error = "Code required"
                verificationCodeNumber.requestFocus()
                return@setOnClickListener
            }

            verificationId?.let {
                val credential = PhoneAuthProvider.getCredential(it, code)
                addPhoneNumber(credential)
            }
        }
    }

    private val mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential : PhoneAuthCredential) {
            addPhoneNumber(phoneAuthCredential)
        }

        override fun onVerificationFailed(exception : FirebaseException) {
            // If verification fails, then displaying the toast message
            Toast.makeText(this@PhoneAuthentication, exception.message!!, Toast.LENGTH_SHORT)
                .show()
        }

        // When the auto verification is not done and the user has to enter the code
        override fun onCodeSent(verificationId : String, token : PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, token)
            this@PhoneAuthentication.verificationId = verificationId
        }
    }

    private fun addPhoneNumber(it: PhoneAuthCredential) {
        mAuth.currentUser?.updatePhoneNumber(it)?.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Phone Number Updated", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}