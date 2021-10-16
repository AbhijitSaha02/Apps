package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var signUp : TextView
    private lateinit var login : TextView
    private lateinit var emailLogin : TextInputEditText
    private lateinit var passwordLogin : TextInputEditText
    private lateinit var progressBar : ProgressBar
    private lateinit var googleSignIn : ImageView
    private lateinit var forgotPassword : TextView
    // Firebase object
    private lateinit var mAuth : FirebaseAuth

    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Instantiating the firebase object
        mAuth = FirebaseAuth.getInstance()

        // Finding the Views
        signUp = findViewById(R.id.signUp)
        login = findViewById(R.id.button_user_login)
        emailLogin = findViewById(R.id.email_login)
        passwordLogin = findViewById(R.id.password_login)
        googleSignIn = findViewById(R.id.button_googleLogin)
        forgotPassword = findViewById(R.id.forgotPassword)

        // Actions to perform when LOGIN is clicked
        login.setOnClickListener {
            val email = emailLogin.text.toString().trim()
            val password = passwordLogin.text.toString().trim()

            // If the Email field is empty, giving an error
            if(email.isEmpty()) {
                emailLogin.error = "Email required"
                emailLogin.requestFocus()
                return@setOnClickListener
            }

            // Checking if the Email entered is valid
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLogin.error = "Valid Email required"
                emailLogin.requestFocus()
                return@setOnClickListener
            }

            // Checking if the Password field is empty or length is less than 6
            if(password.isEmpty() || password.length < 6) {
                passwordLogin.error = "Minimum 6 character password required"
                passwordLogin.requestFocus()
                return@setOnClickListener
            }

            // Calling the function to perform logging in operations
            userLogin(email,password)
        }

        // Opening the RegisterActivity when SignUp is clicked
        signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Setting a onClickListener on Google ImageView
        googleSignIn.setOnClickListener {
            /* Configuring Google Sign In
            default_web_client_id is the oauth 2.0 id which is mentioned in the
            google.jason services folder
             */
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)

            // Calling the functions which will perform of all the sign in operations with google
            signInWithGoogle()
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity((intent))
        }
    }

    // Function for logging in with email and password
    private fun userLogin(email: String, password: String) {
        // Finding a setting the visibility of progressbar
        progressBar = findViewById(R.id.progressBarNext)
        progressBar.visibility = View.VISIBLE

        // Configuring signing in with firebase with email and password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if(task.isSuccessful) {
                // If task is successful making the progressbar invisible again
                progressBar.visibility = View.GONE

                val intent = Intent(this, PhoneAuthentication::class.java).apply {
                    /* Setting flags so that the user cannot see the register activity again
                    if the user clicks back again
                    Starting the PhoneAuthentication Activity by closing all the other activities,
                    since we don't want to show the logging screen again after the task is
                    successful
                     */
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                startActivity(intent)
            }
            else {
                // Showing a error if the task fails
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function for launching or starting an activity with the account options of google
    private var resultLauncher = registerForActivityResult(ActivityResultContracts
        .StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data : Intent? = result.data

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // If the Google SignIn was successful, authenticate with firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Toast.makeText(this, "FireBaseAuthWithGoogle" + account.id, Toast
                        .LENGTH_SHORT).show()
                    firebaseAuthWithGoogle(account.idToken!!)
                }
                catch(e : ApiException) {
                    // Google SignIn failed, so updating Ui appropriately
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Authenticating the account with google
    private fun firebaseAuthWithGoogle(idToken : String) {
        // Getting the credentials of the account
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
               if(task.isSuccessful)  {
                   // if SignIn is successful
                   Toast.makeText(this, "Successfully signed in", Toast.LENGTH_SHORT)
                       .show()
                   val intent = Intent(this, PhoneAuthentication::class.java)
                   startActivity(intent)
               }
                else {
                   // if SignIn is successful
                   Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
               }
            }
    }

    private fun signInWithGoogle() {
        val signInIntent  = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }
}