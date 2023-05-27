package com.example.travelling

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fullNameEditText = findViewById<EditText>(R.id.full_name_edit_text)
        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val phoneNumberEditText = findViewById<EditText>(R.id.phone_number_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val saveButton = findViewById<Button>(R.id.save_button)

        // Load existing profile data into the views, if available
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val fullName = sharedPreferences.getString("full_name", "")
        val email = sharedPreferences.getString("email", "")
        val phoneNumber = sharedPreferences.getString("phone_number", "")
        val password = sharedPreferences.getString("password", "")

        if (fullName != null) {
            fullNameEditText.setText(fullName)
        }
        if (email != null) {
            emailEditText.setText(email)
        }
        if (phoneNumber != null) {
            phoneNumberEditText.setText(phoneNumber)
        }
        if (password != null) {
            passwordEditText.setText(password)
        }


    saveButton.setOnClickListener()
    {
        // Get the user's input
        val fullName = fullNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Save the data in shared preferences
        val editor = sharedPreferences.edit()
        editor.putString("full_name", fullName)
        editor.putString("email", email)
        editor.putString("phone_number", phoneNumber)
        editor.putString("password", password)
        editor.apply()

        // Show a toast message to confirm that the data was saved
        Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()
    }
}}

private fun Button.setOnClickListener() {
    TODO("Not yet implemented")
}
