package com.example.vibeus.Features.Users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class User(
    val id: String,
    val name: String,
    val bio: String,
    val imageUrl: String,
    val age: Int,
    val work: String,
    val college: String,
    val languages: List<String>,
    val datingIntentions: String,
    val religiousBeliefs: String,
    val height: String,
    val drinking: String,
    val smoking: String
)

class OtherUsersViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun fetchUsers() {
        viewModelScope.launch {
            val db = FirebaseFirestore.getInstance()
            try {
                val snapshot = db.collection("users").get().await()
                val fetchedUsers = snapshot.documents.map { document ->
                    User(
                        id = document.id,
                        name = document.getString("name") ?: "",
                        bio = document.getString("bio") ?: "",
                        imageUrl = document.getString("imageUrl") ?: "",
                        age = (document.getLong("age") ?: 0L).toInt(),
                        work = document.getString("work") ?: "",
                        college = document.getString("college") ?: "",
                        languages = (document.get("languages") as? List<String>) ?: emptyList(),
                        datingIntentions = document.getString("datingIntentions") ?: "",
                        religiousBeliefs = document.getString("religiousBeliefs") ?: "",
                        height = document.getString("height") ?: "",
                        drinking = document.getString("drinking") ?: "",
                        smoking = document.getString("smoking") ?: ""
                    )
                }
                _users.value = fetchedUsers
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun setSampleUsers(sampleUsers: List<User>) {
        _users.value = sampleUsers
    }
}