package com.example.submate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class UserSetup : AppCompatActivity() {
    private lateinit var userImage: CircleImageView
    private var selectedImageUri: Uri? = null
    private val defaultImageResId = R.mipmap.man_foreground // Default image resource ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the action bar (optional)
        supportActionBar?.hide()
        setContentView(R.layout.activity_user_setup)

        userImage = findViewById(R.id.userImage)

        // Set the default image to the CircleImageView
        userImage.setImageResource(defaultImageResId)

        // Set an OnClickListener for the profile image
        userImage.setOnClickListener {
            checkGalleryPermission()
        }

        val confirmButton = findViewById<Button>(R.id.continue_button)
        val nameEditText = findViewById<EditText>(R.id.userName)

        confirmButton.setOnClickListener {
            // Get the name entered in the EditText
            val enteredName = nameEditText.text.toString()

            // Generate a random username if no text is entered
            val username = if (enteredName.isNotEmpty()) {
                enteredName
            } else {
                generateRandomUsername()
            }

            // Create an Intent to start the MainActivity
            val intent = Intent(this, MainActivity::class.java)

            // Add the username as an extra to the Intent
            intent.putExtra("name", username)

            // Add the selected image URI as an extra to the Intent
            if (selectedImageUri != null) {
                intent.putExtra("profile_image_uri", selectedImageUri.toString())
            }

            // Start the MainActivity
            startActivity(intent)
        }
    }

    private fun checkGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                IMAGE_PICK_REQUEST
            )
        } else {
            // Permission is already granted, you can open the gallery
            openGalleryForImage()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK) {
            val selectedImage = data?.data

            if (selectedImage != null) {
                // Save the selected image to a file
                val imageFile = createImageFile()
                selectedImageUri = Uri.fromFile(imageFile)

                try {
                    val inputStream = contentResolver.openInputStream(selectedImage)
                    val outputStream = FileOutputStream(imageFile)
                    inputStream?.copyTo(outputStream)
                    inputStream?.close()
                    outputStream.close()

                    // Display the selected image in the userImage ImageView
                    userImage.setImageURI(selectedImageUri)

                    // Add a log statement to check the selectedImageUri
                    Log.d("UserSetup", "Selected image URI: $selectedImageUri")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    private fun generateRandomUsername(): String {
        // Generate a random username, e.g., "User123"
        val random = Random()
        val randomNumber = random.nextInt(1000) // Generate a random number between 0 and 999
        return "User$randomNumber"
    }

    companion object {
        private const val IMAGE_PICK_REQUEST = 1
    }
}
