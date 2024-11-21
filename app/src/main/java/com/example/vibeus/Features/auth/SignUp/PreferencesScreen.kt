package com.example.vibeus.Features.auth.Preferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.wear.compose.material3.Button
import com.example.vibeus.Features.auth.SignUp.SignUpViewModel

@Composable
fun PreferencesScreen(navController: NavController, viewModel: SignUpViewModel = viewModel()) {
    var lookingFor by remember { mutableStateOf("") }
    var dealBreakers by remember { mutableStateOf("") }
    var partnerPersonality by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val saveSuccess by viewModel.saveSuccess.observeAsState()

    LaunchedEffect(saveSuccess) {
        if (saveSuccess == true) {
            navController.navigate("lifestyle") {
                popUpTo("preferences") { inclusive = true }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = lookingFor,
                onValueChange = { lookingFor = it },
                label = { Text("Looking For") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = dealBreakers,
                onValueChange = { dealBreakers = it },
                label = { Text("Deal Breakers") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = partnerPersonality,
                onValueChange = { partnerPersonality = it },
                label = { Text("Partner Personality") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Button(
                    onClick = {
                        loading = true
                        viewModel.savePreferences(lookingFor, dealBreakers, partnerPersonality) { error: String? ->
                            loading = false
                            if (error != null) {
                                errorMessage = error
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
            }
            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview() {
    PreferencesScreen(navController = rememberNavController())
}