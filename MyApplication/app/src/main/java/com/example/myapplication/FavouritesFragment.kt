package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class FavouritesFragment : Fragment() {
    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var recyclerViewFavourites : RecyclerView
    private lateinit var noFavourites: TextView

    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionRef : CollectionReference = db.collection("Users")

    private val emailDetail = FirebaseAuth.getInstance().currentUser?.email.toString()

    private var arrayFavouriteList : ArrayList<FavouriteMovieData> = arrayListOf()

    // Overriding onCreateView function of Fragment class
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    // onViewCreated will execute the layout gets inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewFavourites = view.findViewById(R.id.recyclerView_favourites)
        noFavourites = view.findViewById(R.id.no_favourites)

        recyclerViewFavourites.apply {
            layoutManager = LinearLayoutManager(this.context)

            // Adding properties to the recycler view
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)

            getFavouriteMovies()

        }

    }

    private fun getFavouriteMovies() {

        collectionRef.document(emailDetail).collection("Favourite Movies")
            .addSnapshotListener{ querySnapshot, exception ->
                if(exception != null) {
                    Log.e("Favourites Fragment", exception.message.toString())
                }

                for(docChange : DocumentChange in querySnapshot?.documentChanges!!) {
                    if(docChange.type == DocumentChange.Type.ADDED) {
                        arrayFavouriteList.add(docChange.document
                            .toObject(FavouriteMovieData::class.java))
                    }
                }
                recyclerViewFavourites.adapter = RecyclerAdapterFavourites(arrayFavouriteList)
                recyclerViewFavourites.adapter?.notifyDataSetChanged()
            }
    }
}