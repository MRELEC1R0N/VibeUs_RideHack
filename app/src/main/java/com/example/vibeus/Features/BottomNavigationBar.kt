package com.example.vibeus.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(navController: NavController , modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color(0xFF2A2A3A),
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Profile",
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate("profile") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Map",
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate("map") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = "Chat",
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate("chat") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
        )
    }
}