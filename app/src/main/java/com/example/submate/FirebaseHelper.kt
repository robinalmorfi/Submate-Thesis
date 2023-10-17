package com.example.submate

import com.google.firebase.database.*

object FirebaseHelper {

    private val database = FirebaseDatabase.getInstance()
    private val messagesRef = database.getReference("messages")

    fun sendMessage(messageContent: String, sender: String) {
        val message = Message(messageContent, sender, System.currentTimeMillis())

        // Push the message to the database
        messagesRef.push().setValue(message)
            .addOnSuccessListener {
                // Message sent successfully
            }
            .addOnFailureListener {
                // Handle the error
            }
    }

    fun setupMessageListener(listener: (Message) -> Unit) {
        messagesRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                if (message != null) {
                    listener(message)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle changes to existing messages if needed
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Handle when a message is removed from the database
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle when a message changes position in the list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors in Firebase interactions
            }
        })
    }
}
