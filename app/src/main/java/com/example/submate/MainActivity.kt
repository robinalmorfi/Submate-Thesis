package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the LinearLayouts by their IDs
        val deafLayout = findViewById<View>(R.id.deafLayout)
        val speakerLayout = findViewById<View>(R.id.speakerLayout)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        // Set an OnClickListener for the "chatLayout"
        deafLayout.setOnClickListener {
            // Define the action to open the Transcription activity here
            val intent = Intent(this@MainActivity, Transcription::class.java)
            startActivity(intent)
        }

        speakerLayout.setOnClickListener {
            // Define the action to open the Healthy activity here
            val intent = Intent(this@MainActivity, Speech::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Retrieve the entered name from the extras
                val enteredName = intent.getStringExtra("name")

                // Create an Intent to start the UserProfile activity
                val profileIntent = Intent(this, UserProfile::class.java)

                // Add the entered name as an extra to the Intent
                profileIntent.putExtra("name", enteredName)

                // Add the selected image URI as an extra to the Intent
                val selectedImageUriString = intent.getStringExtra("profile_image_uri")
                if (selectedImageUriString != null) {
                    profileIntent.putExtra("profile_image_uri", selectedImageUriString)
                }

                // Start the UserProfile activity
                startActivity(profileIntent)

                true // Return true to indicate that the event has been handled
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
