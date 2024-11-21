package com.example.vibeus.Features.Chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class User(val id: String, val name: String)

class ChatViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun fetchUsers() {
        viewModelScope.launch {
            val usersList = FirebaseFirestore.getInstance()
                .collection("users")
                .get()
                .await()
                .documents
                .map { document ->
                    User(id = document.id, name = document.getString("name") ?: "")
                }
            _users.value = usersList
        }
    }
}