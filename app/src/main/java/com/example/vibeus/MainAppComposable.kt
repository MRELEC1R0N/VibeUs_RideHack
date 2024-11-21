package com.example.vibeus

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vibeus.Features.Map.MapScreen
import com.example.vibeus.Features.auth.FunQuestions.FunQuestionsScreen
import com.example.vibeus.Features.auth.Lifestyle.LifestyleScreen
import com.example.vibeus.Features.auth.PersonalityTraits.PersonalityTraitsScreen
import com.example.vibeus.Features.auth.Preferences.PreferencesScreen
import com.example.vibeus.Features.auth.SignIn.SignInScreen
import com.example.vibeus.Features.auth.SignUp.SignUpScreen
import com.example.vibeus.Features.auth.ValuesAndBeliefs.ValuesAndBeliefsScreen
import com.example.vibeus.Features.profile.ProfileScreen
import com.example.vibeus.Features.Chat.ChatScreen
import com.example.vibeus.Features.Chat.ChatDetailScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val navController = rememberNavController()
        val currentUser = FirebaseAuth.getInstance().currentUser

        LaunchedEffect(currentUser) {
            if (currentUser != null) {
                navController.navigate("profile") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                SignInScreen(navController)
            }
            composable("register") {
                SignUpScreen(navController)
            }
            composable("personalityTraits") {
                PersonalityTraitsScreen(navController)
            }
            composable("preferences") {
                PreferencesScreen(navController)
            }
            composable("lifestyle") {
                LifestyleScreen(navController)
            }
            composable("valuesAndBeliefs") {
                ValuesAndBeliefsScreen(navController)
            }
            composable("funQuestions") {
                FunQuestionsScreen(navController)
            }
            composable("profile") {
                ProfileScreen(navController)
            }
            composable("map") {
                MapScreen(navController)
            }
            composable("chat") {
                ChatScreen(navController)
            }
            composable("chatDetail/{userId}") { backStackEntry ->
                ChatDetailScreen(navController, backStackEntry.arguments?.getString("userId") ?: "")
            }
        }
    }
}