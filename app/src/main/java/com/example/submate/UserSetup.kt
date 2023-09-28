package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class UserSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the action bar (optional)
        supportActionBar?.hide()

        setContentView(R.layout.activity_user_setup)

        val confirmButton = findViewById<Button>(R.id.continue_button)
        val nameEditText = findViewById<EditText>(R.id.userName)

        confirmButton.setOnClickListener {
            // Get the name entered in the EditText
            val enteredName = nameEditText.text.toString()

            // Create an Intent to start the MainMenu activity
            val intent = Intent(this, MainActivity::class.java)

            // Add the entered name as an extra to the Intent
            intent.putExtra("name", enteredName)

            // Start the MainMenu activity
            startActivity(intent)
        }
    }
}