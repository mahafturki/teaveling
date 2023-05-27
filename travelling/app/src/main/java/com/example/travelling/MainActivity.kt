package com.example.travelling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth





class MainActivity : AppCompatActivity() {
    lateinit var btnSignIn: Button
    lateinit var editTextTextEmailAddress: EditText
    lateinit var editTextNumberPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSignIn = findViewById(R.id.btnSignIn);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextNumberPassword = findViewById(R.id.editTextNumberPassword);

        btnSignIn.setOnClickListener(){

            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
            )

// Create and launch sign-in intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }

    //private fun onButtonClick() {    }

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }



    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
           // print("user:");
            //print(user?.uid);
            //Toast.makeText(this,user?.uid, Toast.LENGTH_SHORT).show()

             Toast.makeText(this,"you are loged in ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity,HomeScreen::class.java))
                finish()
            // ...
        } else {
            Toast.makeText(this,"Error in sign in ", Toast.LENGTH_SHORT).show()

            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }


}