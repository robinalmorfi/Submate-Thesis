package com.example.submate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.net.Uri
import android.util.Log
import de.hdodenhof.circleimageview.CircleImageView

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

        // Find the ImageView with ID userProfile2
        val userProfile2 = findViewById<CircleImageView>(R.id.userProfile2)

        // Retrieve the selected image URI from the extras
        val selectedImageUriString = intent.getStringExtra("profile_image_uri")

        // Check if a valid image URI was passed
        if (selectedImageUriString != null) {
            val selectedImageUri = Uri.parse(selectedImageUriString)

            // Set the selected image URI as the image source for userProfile2
            userProfile2.setImageURI(selectedImageUri)
        }
    }
}
