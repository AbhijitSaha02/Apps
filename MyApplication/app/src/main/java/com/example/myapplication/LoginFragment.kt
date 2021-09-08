package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    lateinit var loginButton : Button

    // Overriding onCreateView function of Fragment class
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_login, container, false)

        // Finding the views with the help of the layout inflater
        loginButton = inflate.findViewById(R.id.button_login)

        return inflate
    }

    // Performing operations after the layout is inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting click listener on LOGIN button
        loginButton.setOnClickListener {
            // Opening LoginActivity when the button is clicked
            val intent = Intent(activity, LoginActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }
}