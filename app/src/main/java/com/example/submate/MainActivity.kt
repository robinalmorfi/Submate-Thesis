package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the LinearLayouts by their IDs
        val deafLayout = findViewById<View>(R.id.deafLayout)
        val speakerLayout = findViewById<View>(R.id.speakerLayout)

        // Set an OnClickListener for the "deafLayout"
        deafLayout.setOnClickListener {
            // Define the action to open the Transcription activity here
            val intent = Intent(this@MainActivity, Transcription::class.java)
            startActivity(intent)
        }

        // Set an OnClickListener for the "speakerLayout"
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

                // Create an Intent to start the UserProfileActivity
                val profileIntent = Intent(this, UserProfile::class.java)

                // Add the entered name as an extra to the Intent
                profileIntent.putExtra("name", enteredName)

                // Start the UserProfileActivity
                startActivity(profileIntent)

                true // Return true to indicate that the event has been handled
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
