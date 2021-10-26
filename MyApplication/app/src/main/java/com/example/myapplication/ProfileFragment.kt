package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {
    private lateinit var pic : ImageView
    private lateinit var imageUri : Uri
    private lateinit var profileName : EditText
    private lateinit var profileEmail : EditText
    private lateinit var profilePhone : EditText
    private lateinit var saveButton : Button
    private lateinit var logOutButton: Button

    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_profile, container, false)

        pic = inflate.findViewById(R.id.profile_picture)
        profileName = inflate.findViewById(R.id.profile_name)
        profileEmail = inflate.findViewById(R.id.profile_email)
        profilePhone = inflate.findViewById(R.id.profile_phone)
        saveButton = inflate.findViewById(R.id.button_save_profile)
        logOutButton = inflate.findViewById(R.id.button_logout)

        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser?.let { user ->

            Glide.with(this)
                .load(user.photoUrl)
                .into(pic)

            profileName.setText(user.displayName)

            profileEmail.setText(user.email)

            profilePhone.setText(user.phoneNumber)
        }

        val userDetail : HashMap<String, Any?> = hashMapOf("name" to currentUser?.displayName,
            "email" to currentUser?.email, "phone" to currentUser?.phoneNumber,
            "image" to currentUser?.photoUrl.toString())

        pic.setOnClickListener {
            takePictureIntent()
        }

        saveButton.setOnClickListener {

            val photo = when {
                ::imageUri.isInitialized -> imageUri
                else -> currentUser?.photoUrl
            }

            val name = profileName.text.toString().trim()
            if(name.isEmpty()) {
                profileName.error = "Name required"
                profileName.requestFocus()
                return@setOnClickListener
            }

            val updates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(photo)
                .build()

            currentUser?.updateProfile(updates)
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Log.d("Profile Fragment", "Profile Updated Successfully")
                    }
                    else {
                        Log.e("Profile Fragment", task.exception?.message.toString())
                    }
                }

            db.collection("Users").document(currentUser?.email.toString()).set(userDetail)
                .addOnSuccessListener {
                    Toast.makeText(this.context, "Details added", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Log.e("Profile Fragment", it.message.toString())
                }
        }

        logOutButton.setOnClickListener {
            AlertDialog.Builder(this.context).apply {
                setTitle("Are You Sure?")
                setPositiveButton("Yes") {  _, _ ->

                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this.context, LoginActivity::class.java)
                    startActivity(intent)

                }

                setNegativeButton("Cancel") {  _, _ ->
                }
            }.create().show()
        }

    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts
        .StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            // Getting the image from the intent
            val imageBitmap = data?.extras?.get("data") as Bitmap

            saveImage(imageBitmap)
        }
    }

    private fun saveImage(imageBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val storageReference = FirebaseStorage.getInstance()
            .reference
            .child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")

        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        val uploadImage =  storageReference.putBytes(image)

        uploadImage.addOnCompleteListener { uploadTask ->
            if(uploadTask.isSuccessful) {
                storageReference.downloadUrl.addOnCompleteListener { urlTask ->
                    urlTask.result?.let {
                        imageUri = it
                        Log.d("Profile Fragment", imageUri.toString())

                        pic.setImageBitmap(imageBitmap)
                    }
                }
            }
            else {
                uploadTask.exception?.let {
                    Log.e("Profile Fragment", it.message.toString())
                }
            }
        }
    }

    private fun takePictureIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }
}