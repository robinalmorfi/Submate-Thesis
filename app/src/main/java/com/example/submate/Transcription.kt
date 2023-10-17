
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submate.FirebaseHelper
import com.example.submate.Message


class Transcription : AppCompatActivity() {

    // ... Your existing code ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Add this line
        // ... Your existing initialization code ...

        // Setup message listener
        FirebaseHelper.setupMessageListener { message ->
            runOnUiThread {
                displayMessage(message)
            }
        }
    }

    private fun displayMessage(message: Message) {
        // Update your UI to display the message
    }

    private fun sendMessage(messageContent: String, sender: String) {
        FirebaseHelper.sendMessage(messageContent, sender)
    }

}
