package com.example.submate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Retrieve the entered text from the Intent
        val intent = intent
        val enteredText = intent.getStringExtra("name")

        // Display the text in a TextView or any other view as needed
        val textView = findViewById<TextView>(R.id.app_name_textview)
        textView.text = enteredText
    }
}