package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var detailToolbar : androidx.appcompat.widget.Toolbar
    private lateinit var detailCollapsingToolbar : CollapsingToolbarLayout
    private lateinit var detailImage : ImageView
    private lateinit var detailOriginalTitle : TextView
    private lateinit var detailOriginalLanguage : TextView
    private lateinit var detailReleaseDate : TextView
    private lateinit var detailRating : TextView
    private lateinit var detailPopularity : TextView
    private lateinit var detailGenre : TextView
    private lateinit var detailDescription : TextView
    private lateinit var addToFavouritesButton : Button
    private lateinit var removeFromFavouritesButton : Button
    private lateinit var recyclerViewTrailer : RecyclerView
    private lateinit var noTrailer : TextView

    private val emailDetail = FirebaseAuth.getInstance().currentUser?.email.toString()

    private val BASE_URL = "https://api.themoviedb.org/"

    private lateinit var retrofit : Retrofit

    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionRef : CollectionReference = db.collection("Users")

    private var id : Int? = null
    private var image : String? = null
    private var title : String? = null
    private var originalTitle : String? = null
    private var originalLanguage : String? = null
    private var releaseDate : String? = null
    private var rating : Float? = null
    private var popularity : Double? = null
    private var genre : String? = null
    private var description: String? = null
    private var video : Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        detailToolbar = findViewById(R.id.toolbar_movie_detail)
        setSupportActionBar(detailToolbar)

        detailCollapsingToolbar = findViewById(R.id.collapsing_toolbar_movie_detail)

        detailImage = findViewById(R.id.detail_poster)
        detailOriginalTitle = findViewById(R.id.detail_original_title)
        detailOriginalLanguage = findViewById(R.id.detail_original_language)
        detailReleaseDate = findViewById(R.id.detail_release_date)
        detailRating = findViewById(R.id.detail_rating)
        detailPopularity = findViewById(R.id.detail_popularity)
        detailGenre = findViewById(R.id.detail_genre)
        detailDescription = findViewById(R.id.detail_description)
        addToFavouritesButton = findViewById(R.id.button_favourite)
        removeFromFavouritesButton = findViewById(R.id.button_remove_favourite)
        noTrailer = findViewById(R.id.no_trailer)

        if(intent.hasExtra("original_title")) {
            id = intent.extras?.getInt("id")
            image = "https://image.tmdb.org/t/p/w500/" +
                    intent.extras?.getString("poster_path")
            title = intent.extras?.getString("title")
            originalTitle = intent.extras?.getString("original_title")
            originalLanguage = intent.extras?.getString("original_language")
            releaseDate = intent.extras?.getString("release_date")
            rating = intent.extras?.getFloat("vote_average")
            popularity = intent.extras?.getDouble("popularity")
            genre = intent.extras?.getString("genre_ids")
            description = intent.extras?.getString("overview")
            video = intent.extras?.getBoolean("video")


            Glide.with(this)
                .load(image)
                .into(detailImage)

            detailOriginalTitle.text = originalTitle
            detailOriginalLanguage.text = originalLanguage
            detailReleaseDate.text = releaseDate
            detailRating.text = rating.toString()
            detailPopularity.text = popularity.toString()
            detailGenre.text = genre
            detailDescription.text = description

            detailCollapsingToolbar.title = title
            detailCollapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            detailCollapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        }
        else {
            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()
        }

        watchMovieTrailer(video, id)

        var f : Int = 0

        collectionRef.document(emailDetail).collection("Favourite Movies")
            .addSnapshotListener{ querySnapshot, exception ->
                if(exception != null) {
                    Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                }

                for(docChange : DocumentChange in querySnapshot?.documentChanges!!) {
                    Log.d("Already added docs", docChange.document.id)
                    if(docChange.document.id.compareTo(title!!.toString()) == 0) {
                        f = 1
                    }
                }

            }

        if(f == 0) {
            addToFavouritesButton.visibility = View.VISIBLE
            removeFromFavouritesButton.visibility = View.GONE
        }
        else {
            removeFromFavouritesButton.visibility = View.VISIBLE
            addToFavouritesButton.visibility = View.GONE
        }

        val favourite : HashMap<String, Any?> = hashMapOf("id" to id, "poster_path" to image,
            "title" to title, "original_title" to originalTitle, "original_language" to originalLanguage,
            "release_date" to releaseDate, "vote_average" to rating, "popularity" to popularity,
            "genre_ids" to genre, "overview" to description, "video" to video)


        addToFavouritesButton.setOnClickListener {
            addToFavourite(favourite)

            addToFavouritesButton.visibility = View.GONE
            removeFromFavouritesButton.visibility = View.VISIBLE
        }

        removeFromFavouritesButton.setOnClickListener {
            removeFromFavourite()

            removeFromFavouritesButton.visibility = View.GONE
            addToFavouritesButton.visibility = View.VISIBLE
        }
    }

    private fun watchMovieTrailer(video : Boolean?, id : Int?) {
        recyclerViewTrailer = findViewById(R.id.recyclerView_trailer)
        recyclerViewTrailer.apply {
            layoutManager = LinearLayoutManager(this.context)

            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )

            setHasFixedSize(true)

            getTrailer(video, id)
        }

    }

    private fun getTrailer(video : Boolean?, id : Int?) {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiInterface = retrofit.create(MovieApiInterface::class.java)

        movieApiInterface.getMovieVideoList(id)
            .enqueue(object : Callback<MovieVideo> {
                override fun onResponse(
                    call: Call<MovieVideo>,
                    response: Response<MovieVideo>) {
                    val data = response.body()?.results
                    if(! data.isNullOrEmpty()) {
                        noTrailer.visibility = View.GONE
                        recyclerViewTrailer.visibility = View.VISIBLE

                        recyclerViewTrailer.apply {
                            adapter = RecyclerAdapterTrailer(data)
                            adapter?.notifyDataSetChanged()
                        }

                    }
                    else {
                        recyclerViewTrailer.visibility = View.GONE
                        noTrailer.visibility = View.VISIBLE
                    }
                    Toast.makeText(this@MovieDetailActivity, "Successful",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<MovieVideo>, t: Throwable) {
                    Toast.makeText(this@MovieDetailActivity, "UnSuccessful",
                        Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun addToFavourite(favourite : HashMap<String, Any?>) {
        collectionRef.document(emailDetail).collection("Favourite Movies")
            .document(title.toString()).set(favourite)
            .addOnSuccessListener {
                Toast.makeText(this@MovieDetailActivity, "Added to Favourites",
                    Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this@MovieDetailActivity, it.toString(),
                    Toast.LENGTH_SHORT).show()
            }
    }

    private fun removeFromFavourite() {
        collectionRef.document(emailDetail).collection("Favourite Movies")
            .document(title.toString()).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Removed from Favourites", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this@MovieDetailActivity, it.toString(),
                    Toast.LENGTH_SHORT).show()
            }
    }

}