package com.example.vibeus.Features.Users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.vibeus.R
import kotlinx.coroutines.launch
import com.google.accompanist.flowlayout.FlowRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherUsersScreen(navController: NavController, viewModel: OtherUsersViewModel = viewModel()) {
    val users by viewModel.users.collectAsState()
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    if (users.isNotEmpty()) {
        val currentUser = users[currentIndex]

        Scaffold(
            topBar = {
                OtherUsersTopBar(navController)
            }
        ) { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1E1E2A)) // Background color
                    .padding(paddingValues)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    UserImageCard(currentUser)
                    Spacer(modifier = Modifier.height(16.dp))
                    AboutMeCard(currentUser)
                    Spacer(modifier = Modifier.height(16.dp))
                    WorkAndEducationCard(currentUser)
                    Spacer(modifier = Modifier.height(16.dp))
                    MoreAboutMeCards(currentUser)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { currentIndex = (currentIndex + 1) % users.size }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Dislike",
                                tint = Color.Red
                            )
                        }
                        IconButton(onClick = { currentIndex = (currentIndex + 1) % users.size }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Like",
                                tint = Color.Green
                            )
                        }
                        IconButton(onClick = { currentIndex = (currentIndex + 1) % users.size }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Accept",
                                tint = Color.Blue
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoreAboutMeCards(user: User) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        SmallCard(icon = Icons.Default.Favorite, text = user.datingIntentions)
        SmallCard(icon = Icons.Default.Info, text = user.religiousBeliefs)
        SmallCard(icon = Icons.Default.Person, text = user.height)
        SmallCard(icon = Icons.Default.LocationOn, text = user.drinking)
        SmallCard(icon = Icons.Default.Create, text = user.smoking)
    }
}

@Composable
fun SmallCard(icon: ImageVector, text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF2E2E3A))
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
fun WorkAndEducationCard(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2E2E3A)) // Same background color
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding around the text
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Work",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Work",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.work,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "College",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "College",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.college,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Languages",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Languages I know",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.languages.joinToString(", "),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun UserImageCard(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        AsyncImage(
            model = user.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            error = painterResource(R.drawable.ic_launcher_foreground) // Use a placeholder image resource
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 300f
                    )
                )
        ) {
            Text(
                text = "${user.name}, ${user.age}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun AboutMeCard(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2E2E3A)) // Light color card
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding around the text
        ) {
            Text(
                text = "About me",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.bio,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherUsersTopBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF1E1E2A)
        ),
        title = {
            Text(
                text = "Vibe Us",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("profile")
                    }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OtherUsersScreenPreview() {
    val mockViewModel = OtherUsersViewModel().apply {
        viewModelScope.launch {
            val sampleUsers = listOf(
                User(
                    id = "1",
                    name = "Alice",
                    bio = "Loves hiking and outdoor adventures.",
                    imageUrl = "",
                    age = 25,
                    work = "Software Engineer",
                    college = "MIT",
                    languages = listOf("English", "Spanish"),
                    datingIntentions = "Looking for a serious relationship",
                    religiousBeliefs = "Atheist",
                    height = "5'6\"",
                    drinking = "Occasionally",
                    smoking = "No"
                ),
                User(
                    id = "2",
                    name = "Bob",
                    bio = "Avid reader and coffee enthusiast.",
                    imageUrl = "",
                    age = 30,
                    work = "Data Scientist",
                    college = "Stanford",
                    languages = listOf("English", "French"),
                    datingIntentions = "Open to anything",
                    religiousBeliefs = "Christian",
                    height = "5'9\"",
                    drinking = "Socially",
                    smoking = "No"
                ),
                User(
                    id = "3",
                    name = "Charlie",
                    bio = "Tech geek and gamer.",
                    imageUrl = "",
                    age = 28,
                    work = "Game Developer",
                    college = "Harvard",
                    languages = listOf("English", "Japanese"),
                    datingIntentions = "Just looking for friends",
                    religiousBeliefs = "Buddhist",
                    height = "5'8\"",
                    drinking = "Never",
                    smoking = "No"
                ),
                User(
                    id = "4",
                    name = "Diana",
                    bio = "Fitness enthusiast and yoga instructor.",
                    imageUrl = "",
                    age = 26,
                    work = "Yoga Instructor",
                    college = "UCLA",
                    languages = listOf("English", "German"),
                    datingIntentions = "Looking for a serious relationship",
                    religiousBeliefs = "Hindu",
                    height = "5'5\"",
                    drinking = "Occasionally",
                    smoking = "No"
                ),
                User(
                    id = "5",
                    name = "Eve",
                    bio = "Music lover and aspiring musician.",
                    imageUrl = "",
                    age = 24,
                    work = "Musician",
                    college = "Berklee College of Music",
                    languages = listOf("English", "Italian"),
                    datingIntentions = "Open to anything",
                    religiousBeliefs = "Jewish",
                    height = "5'7\"",
                    drinking = "Socially",
                    smoking = "No"
                )
            )
            setSampleUsers(sampleUsers)
        }
    }
    OtherUsersScreen(navController = rememberNavController(), viewModel = mockViewModel)
}