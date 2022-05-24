package com.example.servicebookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.servicebookingapp.MainActivity
import com.example.servicebookingapp.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit

class PhoneLoginActivity : AppCompatActivity() {
    private lateinit var namePhone: EditText
    private lateinit var codePicker: CountryCodePicker
    private lateinit var phoneNumber: EditText
    private lateinit var sendOtp: Button
    private lateinit var otpText: EditText
    private lateinit var resendOtp: TextView
    private lateinit var verifyOtp: Button
    private lateinit var progressBarPhone: ProgressBar

    private lateinit var mAuth: FirebaseAuth

    private var storedVerificationId : String? = null
    private var resendToken : String? = null
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        namePhone = findViewById(R.id.name_phone_login)
        codePicker = findViewById(R.id.countryCodePicker_phone_login)
        phoneNumber = findViewById(R.id.phoneNumber_phone_login)
        sendOtp = findViewById(R.id.button_send_otp)
        otpText = findViewById(R.id.text_otp)
        resendOtp = findViewById(R.id.text_resend_otp)
        verifyOtp = findViewById(R.id.button_verify_otp)
        progressBarPhone = findViewById(R.id.progressBar_phone_login)

        // Set status bar blue
        window.statusBarColor = ContextCompat.getColor(this, androidx.appcompat.R.color.material_blue_grey_800)

        mAuth = FirebaseAuth.getInstance()

        // Things to be done on pressing Send button
        sendOtp.setOnClickListener {

            codePicker.registerCarrierNumberEditText(phoneNumber)

            val checkNumber = phoneNumber.text.toString()
            val completePhoneNumber = codePicker.fullNumberWithPlus
            val name = namePhone.text.toString()

            // Checking if number is valid
            if (!codePicker.isValidFullNumber){
                phoneNumber.error = "Enter a Valid Phone Number"
                phoneNumber.requestFocus()
                return@setOnClickListener
            }

            // sending otp
            val options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(completePhoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(phoneAuthCallBack)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

            // Hiding the after receive OTP widgets
            namePhone.visibility = View.GONE
            codePicker.visibility = View.GONE
            phoneNumber.visibility = View.GONE
            sendOtp.visibility = View.GONE
            otpText.visibility = View.VISIBLE
            resendOtp.visibility = View.VISIBLE
            verifyOtp.visibility = View.VISIBLE

        }

        // Things to be done on pressing Resend button
        resendOtp.setOnClickListener {

            codePicker.registerCarrierNumberEditText(phoneNumber)

            val completePhoneNumber = codePicker.fullNumberWithPlus
            val name = namePhone.text.toString()

            // Sending otp
            val options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(completePhoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(phoneAuthCallBack)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

        }


        verifyOtp.setOnClickListener {

            progressBarPhone.visibility = View.VISIBLE
            val code = otpText.text.toString()
            codePicker.registerCarrierNumberEditText(phoneNumber)

            val phoneNumber = codePicker.fullNumberWithPlus
            val name = namePhone.text.toString()

            if (code.isEmpty()){
                otpText.error = "Code Required"
                otpText.requestFocus()
                return@setOnClickListener
            }

            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)

            signInWithPhoneAuthCredential(credential, phoneNumber , name)
            progressBarPhone.visibility = View.VISIBLE
        }

    }
    private val phoneAuthCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            codePicker.registerCarrierNumberEditText(phoneNumber)

            val phoneNumber = codePicker.fullNumberWithPlus
            val name = namePhone.text.toString()

            Log.d("Verification", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential, phoneNumber, name)
            progressBarPhone.visibility = View.VISIBLE
        }

        override fun onVerificationFailed(e: FirebaseException) {
            /* This callback is invoked in an invalid request for verification is made,
            for instance if the the phone number format is not valid.
             */
            Log.w("Verification" ,"onVerificationFailed", e)
            Toast.makeText(this@PhoneLoginActivity, e.message, Toast.LENGTH_SHORT).show()

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d("Code", "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            // resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        phoneNumber: String,
        name: String
    ) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    //  task.result.user.

                    val user = hashMapOf(

                        "name" to name ,
                        "email" to "",
                        "phone" to phoneNumber,
                        "user_id" to task.result.user!!.uid,
                        "photo_url" to task.result.user!!.photoUrl.toString()
                    )

                    db.collection("users")
                        .document(task.result.user!!.uid)
                        .set(user)
                        .addOnSuccessListener {
                            Log.d("data in firestore" , "true")
                        }
                        .addOnFailureListener {
                            Log.d("data in firestore", it.message.toString() )
                        }


                    progressBarPhone.visibility = View.INVISIBLE
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()


                } else {
                    // Sign in failed, display a message and update the UI
                    progressBarPhone.visibility = View.INVISIBLE
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, "Invalid Code", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}