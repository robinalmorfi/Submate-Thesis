package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val deafButton = findViewById<Button>(R.id.deaf_button)
        deafButton.setOnClickListener {
            val intent = Intent(this, Transcription::class.java )
            startActivity(intent)
        }

        val hearingButton = findViewById<Button>(R.id.hearing_button)
        hearingButton.setOnClickListener {
            val intent = Intent(this, HealthySpeaker::class.java )
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_profile -> {
                // Create an Intent to start the ProfileActivity
                val profileIntent = Intent(this, Profile::class.java)

                // Start the ProfileActivity
                startActivity(profileIntent)

                true // Return true to indicate that the event has been handled
            }
            R.id.action_settings -> {
                // Create an Intent to start the SettingsActivity (replace with the actual name of your SettingsActivity)
                val settingsIntent = Intent(this, Settings::class.java)

                // Start the SettingsActivity
                startActivity(settingsIntent)

                true // Return true to indicate that the event has been handled
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}