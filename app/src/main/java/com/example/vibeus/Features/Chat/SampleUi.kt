import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessagingAppUI() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E2A)) // Background color
    ) {
        Column {
            TopBar()
            MessagesList()
        }
        BottomBar(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = Color(0xFF1E1E2A), // Same as background
        elevation = 0.dp
    ) {
        Text(
            text = "Messages",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun MessagesList() {
    Column(Modifier.padding(16.dp)) {
        val messages = listOf(
            "Guides" to "Hey, what's up?",
            "Travel" to "That sounds cool. What...",
            "Music" to "I like to do a lot of different...",
            "Coding" to "That's awesome."
        )

        for ((name, preview) in messages) {
            MessageItem(name = name, preview = preview)
        }
    }
}

@Composable
fun MessageItem(name: String, preview: String) {
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
                text = name.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = preview,
                color = Color(0xFFAAAAAA),
                fontSize = 14.sp
            )
        }
        Box(
            Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Color(0xFF9C4FFF))
        )
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
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
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessagingAppUI() {
    MessagingAppUI()
}
