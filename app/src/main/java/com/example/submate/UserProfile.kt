package com.example.submate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Find the TextView with ID textView2
        val textView2 = findViewById<TextView>(R.id.textView2)

        // Retrieve the entered name from the extras
        val enteredName = intent.getStringExtra("name")

        // Set the text of textView2 to the entered name
        textView2.text = enteredName

        // Rest of your UserProfile activity code...
    }
}