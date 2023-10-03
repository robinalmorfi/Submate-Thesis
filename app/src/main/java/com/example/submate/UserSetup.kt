package com.example.submate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import de.hdodenhof.circleimageview.CircleImageView
import android.provider.MediaStore
import android.os.Environment
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class UserSetup : AppCompatActivity() {
    private lateinit var userImage: CircleImageView
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the action bar (optional)
        supportActionBar?.hide()
        setContentView(R.layout.activity_user_setup)

        userImage = findViewById(R.id.userImage)

        // Set an OnClickListener for the profile image
        userImage.setOnClickListener {
            openGalleryForImage()
        }

        val confirmButton = findViewById<Button>(R.id.continue_button)
        val nameEditText = findViewById<EditText>(R.id.userName)

        confirmButton.setOnClickListener {
            // Get the name entered in the EditText
            val enteredName = nameEditText.text.toString()

            // Create an Intent to start the MainMenu activity
            val intent = Intent(this, MainActivity::class.java)

            // Add the entered name as an extra to the Intent
            intent.putExtra("name", enteredName)

            // Add the selected image URI as an extra to the Intent
            if (selectedImageUri != null) {
                intent.putExtra("profile_image_uri", selectedImageUri.toString())
            }

            // Start the MainMenu activity
            startActivity(intent)
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
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    companion object {
        private const val IMAGE_PICK_REQUEST = 1
    }
}
