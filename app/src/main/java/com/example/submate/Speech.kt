package com.example.submate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Speech : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val imageBack = findViewById<View>(R.id.imageBack)

        imageBack.setOnClickListener {
            onBackPressed()
        }
    }
}
