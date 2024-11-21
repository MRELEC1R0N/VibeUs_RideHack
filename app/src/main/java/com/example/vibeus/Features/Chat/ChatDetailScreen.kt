package com.example.vibeus.Features.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vibeus.Features.BottomNavigationBar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(navController: NavController, userId: String) {
    var message by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>() }

    LaunchedEffect(userId) {
        val db = FirebaseFirestore.getInstance()
        db.collection("chats")
            .document(userId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    messages.clear()
                    messages.addAll(snapshot.documents.map { document ->
                        Message(
                            text = document.getString("text") ?: "",
                            timestamp = document.getLong("timestamp") ?: 0L
                        )
                    })
                }
            }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E2A)) // Background color
    ) {
        Column {
            TopBar()
            MessagesList(messages)
        }
        BottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = message,
            onMessageChange = { message = it },
            onSend = {
                sendMessage(userId, message)
                message = ""
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Chat",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1E1E2A)
        )
    )
}



@Composable
fun MessagesList(messages: List<Message>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(messages) { message ->
            MessageItem(message.text)
        }
    }
}

@Composable
fun MessageItem(text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFF3A3A4A))
        ) {
            Text(
                text = text.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    message: String,
    onMessageChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color(0xFF2A2A3A),
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "Send",
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onSend)
        )
    }
}

data class Message(val text: String, val timestamp: Long)

private fun sendMessage(userId: String, message: String) {
    val db = FirebaseFirestore.getInstance()
    val messageData = hashMapOf(
        "text" to message,
        "timestamp" to System.currentTimeMillis()
    )
    db.collection("chats")
        .document(userId)
        .collection("messages")
        .add(messageData)
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    ChatDetailScreen(navController = rememberNavController(), userId = "testUserId")
}