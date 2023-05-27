package com.example.travelling

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeScreen : AppCompatActivity() {
    lateinit var profileLayout: LinearLayoutCompat
    lateinit var destinationLayout: LinearLayoutCompat
    lateinit var travelGuideLayout: LinearLayoutCompat
    lateinit var btnLogout: AppCompatButton

    val auth = Firebase.auth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        initViews()

        profileLayout.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        destinationLayout.setOnClickListener {
            val intent = Intent(this, Maps::class.java)
            startActivity(intent)
        }

        travelGuideLayout.setOnClickListener {
            val intent = Intent(this, Amadous::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@HomeScreen, MainActivity::class.java))
            finishAffinity()
        }
        val url =
            "https://www.otelz.com/en/hotel/adam-eve-hotel?to=2153&gclid=CjwKCAjwscGjBhAXEiwAswQqNOblKfJkP2_Qdu7njWebLcBlTxJLAtlxS-nsMuAZadSSM3gBMTaTYRoC2zEQAvD_BwE"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val listener = View.OnClickListener {
            startActivity(intent)
        }

// Attach the listener to your image view
        val imageView = findViewById<ImageView>(R.id.imageView4)
        imageView.setOnClickListener(listener)

    }



    private fun initViews() {
        profileLayout = findViewById(R.id.profileLayout)
        destinationLayout = findViewById(R.id.destinationLayout)
        travelGuideLayout = findViewById(R.id.travelGuideLayout)
        btnLogout  = findViewById(R.id.btnLogout)


    }
}
