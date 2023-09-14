package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val deafButton = findViewById<Button>(R.id.deaf_button)
        deafButton.setOnClickListener {
            val intent = Intent(this, DeafUser::class.java )
            startActivity(intent)
        }

        val hearingButton = findViewById<Button>(R.id.hearing_button)
        hearingButton.setOnClickListener {
            val intent = Intent(this, HealthySpeaker::class.java )
            startActivity(intent)
        }
    }
}