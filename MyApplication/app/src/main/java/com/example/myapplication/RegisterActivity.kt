package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : AppCompatActivity() {
    private lateinit var register : Button
    private lateinit var usernameRegister : TextInputEditText
    private lateinit var emailRegister : TextInputEditText
    private lateinit var passwordRegister : TextInputEditText
    private lateinit var progressBar : ProgressBar
    private lateinit var googleSignUp : Button
    private lateinit var phoneRegister : Button
    // Firebase object
    private lateinit var mAuth : FirebaseAuth

    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Instantiating the firebase object
        mAuth = FirebaseAuth.getInstance()

        // Finding the Views
        register = findViewById(R.id.button_user_register)
        usernameRegister = findViewById(R.id.username_register)
        emailRegister = findViewById(R.id.email_register)
        passwordRegister = findViewById(R.id.password_register)
        googleSignUp = findViewById(R.id.button_google_register)
        phoneRegister = findViewById(R.id.user_phone_register)

        // Actions to perform when SIGNUP is clicked
        register.setOnClickListener {
            val username = usernameRegister.text.toString().trim()
            val email = emailRegister.text.toString().trim()
            val password = passwordRegister.text.toString().trim()

            // When the Username field is empty
            if(username.isEmpty()) {
                usernameRegister.error = "Username required"
                usernameRegister.requestFocus()
                return@setOnClickListener
            }

            // When the Email field is empty
            if(email.isEmpty()) {
                emailRegister.error = "Email required"
                emailRegister.requestFocus()
                return@setOnClickListener
            }

            // Checking if the Email entered is not valid
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailRegister.error = "Valid Email required"
                emailRegister.requestFocus()
                return@setOnClickListener
            }

            // When the Password field is empty or length is less than 6
            if(password.isEmpty() || password.length < 6) {
                passwordRegister.error = "Minimum 6 character password required"
                passwordRegister.requestFocus()
                return@setOnClickListener
            }

            userRegister(email,password)
        }

        // Setting a onClickListener on Google ImageView
        googleSignUp.setOnClickListener {
            // Configuring Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)

            signUpWithGoogle()
        }

        phoneRegister.setOnClickListener {
            phoneUserRegister()
        }
    }

    private fun userRegister(email: String, password: String) {
        // Finding a setting the visibility of progressbar
        progressBar = findViewById(R.id.progressBarRegister)
        progressBar.visibility = View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if(task.isSuccessful) {
                // If task is successful making the progressbar invisible again
                progressBar.visibility = View.GONE

                // Sending a verification email to the user
                mAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                    if(it.isSuccessful) {
                        Log.d("Register Activity", "Verification E-mail Sent")

                        val intent = Intent(this, LoginActivity::class.java).apply {
                            // Flags so that the user cannot see the register activity again
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }

                        startActivity(intent)
                    }
                    // If there is error due to which email wasn't send
                    else {
                        Log.e("Register Activity", it.exception?.message.toString())
                    }
                }

            }
            else {
                Log.e("Register Activity", task.exception?.message.toString())
            }
        }
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts
        .StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data : Intent? = result.data

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // If the Google SignIn was successful, authenticate with firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("Register Activity", "FireBaseAuthWithGoogle " + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                }
                catch(e : ApiException) {
                    // Google SignIn failed, so displaying message appropriately
                    Log.e("Register Activity", e.message.toString())
                }
            }
            else {
                Log.e("Register Activity", exception?.message.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken : String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful)  {
                    // if SignIn is successful
                    Log.d("Register Activity", "Sign In Successful")

                    val intent = Intent(this, LoginActivity::class.java).apply {
                        // Flags so that the user cannot see the register activity again
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }

                    startActivity(intent)
                }
                else {
                    // if SignIn is successful
                    Log.e("Register Activity", task.exception?.message.toString())
                }
            }
    }

    private fun signUpWithGoogle() {

        val signInIntent  = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun phoneUserRegister() {
        val intent = Intent(this, PhoneAuthentication::class.java).apply {
            // Flags so that the user cannot see the register activity again
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        startActivity(intent)
    }
}