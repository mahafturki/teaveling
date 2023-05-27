package com.example.travelling

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.PrecomputedText
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.domain.resources.FlightOfferSearch
import com.amadeus.android.succeeded
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Amadous : AppCompatActivity() {

    private lateinit var searchButton: Button
    private lateinit var resultsText: TextView
    private lateinit var amadeus: Amadeus
    private lateinit var etOriginAirport: EditText
    private lateinit var etDestinationAirport: EditText
    private lateinit var etStartDate: EditText
    private lateinit var etReturnDate: EditText



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amadous)

        initViews()

        amadeus = Amadeus.Builder(this@Amadous)
            .setClientId("pfRYlV5s3jaBjGNdiyghrCIxSgLAcpWz")
            .setClientSecret("iUGAKrf6rn42GzWa")
            .build()

        searchButton.setOnClickListener {

            if(etOriginAirport.text.toString().isNotEmpty() &&
                    etDestinationAirport.text.toString().isNotEmpty() &&
                    etStartDate.text.toString().isNotEmpty() &&
                    etReturnDate.text.toString().isNotEmpty()){
                searchFlights()
            }
            else{
                Toast.makeText(this@Amadous,"Field's can't be empty.",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun initViews() {
        searchButton = findViewById(R.id.search_button)
        resultsText = findViewById(R.id.results_text)
        etOriginAirport = findViewById(R.id.etOriginAirport)
        etDestinationAirport = findViewById(R.id.etDestinationAirport)
        etStartDate = findViewById(R.id.etStartDate)
        etReturnDate = findViewById(R.id.etReturnDate)

    }

    private fun searchFlights() {
        var flightOffersSearches: ApiResult<List<FlightOfferSearch>>? = null
        // Flight Offer Search v2 GET
        GlobalScope.launch {
           flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                originLocationCode = etOriginAirport.text.toString(), // starting airport
                destinationLocationCode = etDestinationAirport.text.toString(),
                departureDate = etStartDate.text.toString(),
                returnDate = etReturnDate.text.toString(),
                adults = 1,
                max = 3)

            Log.e(TAG, "searchFlights: ${Gson().toJson(flightOffersSearches)}" )
            runOnUiThread {
                if (flightOffersSearches != null && flightOffersSearches!!.succeeded) {
                    val flightOffers = flightOffersSearches as ApiResult.Success
                    Log.e(TAG, "searchFlights: $flightOffers")
                    Log.e(TAG, "searchFlights: ${flightOffers.data.size}")

                    val text = StringBuilder()
                    for (offer in flightOffers.data) {
                        text.append(
                            "${offer.itineraries?.get(0)?.segments?.get(0)?.departure?.iataCode} to ${
                                offer.itineraries?.get(
                                    0
                                )?.segments?.get(0)?.arrival?.iataCode
                            }: ${offer.price?.total} ${offer.price?.currency}\n"
                        )
                    }
                    resultsText.text = text.toString()

                } else {
                    resultsText.text = "Error: ${flightOffersSearches as ApiResult.Error}"


                }

                        }
                    }
                }
            }





        /*val it = null
        val response = it.flightOffersSearches
        if (response.isSuccessful) {
            val flightOffers = response.data
            val text = StringBuilder()
            for (offer in flightOffers) {
                text.append("${offer.itineraries[0].segments[0].departure.iataCode} to ${offer.itineraries[0].segments[0].arrival.iataCode}: ${offer.price.total} ${offer.price.currency}\n")
            }
            resultsText.text = text.toString()
        } else {
            resultsText.text = "Error: ${response.statusCode} ${response.statusDescription}"
        }*/
    



