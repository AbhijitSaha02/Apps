package com.example.servicebookingapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var locationText: TextView
    private lateinit var geocoder: Geocoder
    private lateinit var addresses: List<Address>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    // Options
    private lateinit var womenSalon: CardView
    private lateinit var menSalon: CardView
    private lateinit var applianceRepairs: CardView
    private lateinit var cleaning: CardView
    private lateinit var homeRepairs: CardView
    private lateinit var homePainting: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)
        locationText = inflate.findViewById(R.id.location_text)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        womenSalon = view.findViewById(R.id.women_salon)
        menSalon = view.findViewById(R.id.men_salon)
        applianceRepairs = view.findViewById(R.id.appliance_repair)
        cleaning = view.findViewById(R.id.cleaning_pest)
        homeRepairs = view.findViewById(R.id.home_repairs)
        homePainting = view.findViewById(R.id.home_painting)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireView().context)
        geocoder = Geocoder(requireView().context, Locale.getDefault())

        // Runtime Permission
        if (ContextCompat.checkSelfPermission(requireView().context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }

        getLocationUpdates()

        womenSalon.setOnClickListener {
            val intent = Intent(requireView().context, WomenOptionActivity::class.java)
            startActivity(intent)
        }

        menSalon.setOnClickListener {
            val intent = Intent(requireView().context, MenOptionActivity::class.java)
            startActivity(intent)
        }

        applianceRepairs.setOnClickListener {
            val intent = Intent(requireView().context, ApplianceRepairActivity::class.java)
            startActivity(intent)
        }

        cleaning.setOnClickListener {
            val intent = Intent(requireView().context, PestCleaningActivity::class.java)
            startActivity(intent)
        }

        homeRepairs.setOnClickListener {
            val intent = Intent(requireView().context, HomeRepairActivity::class.java)
            startActivity(intent)
        }

        homePainting.setOnClickListener {
            val intent = Intent(requireView().context, OptionsActivity::class.java)
            intent.putExtra("title", "Home Painting")
            startActivity(intent)
        }
    }

    private fun getLocationUpdates()
    {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireView().context)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {

                if (locationResult.locations.isNotEmpty()) {
                    // get latest location
                    val location = locationResult.lastLocation

                    addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    val address: String = addresses[0].getAddressLine(0)
                    val area: String = addresses[0].locality
                    val city: String = addresses[0].adminArea
                    val country: String = addresses[0].countryName
                    val postalCode: String = addresses[0].postalCode

                    val fullAddress = "$address, $area, $city, $country, $postalCode"

                    locationText.text = fullAddress
                    locationText.movementMethod = ScrollingMovementMethod()
                    locationText.setHorizontallyScrolling(true)
                }


            }
        }.also { locationCallback = it }
    }

    //start location updates
    private fun startLocationUpdates() {
        // Runtime Permission
        if (ActivityCompat.checkSelfPermission(
                requireView().context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireView().context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(MainActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper() /* Looper */
        )
    }

    // stop location updates
    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // stop receiving location update when activity not visible/foreground
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    // start receiving location update when activity  visible/foreground
    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}