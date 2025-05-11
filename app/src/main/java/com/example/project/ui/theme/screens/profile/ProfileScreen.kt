package com.example.project.ui.theme.screens.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@Composable
fun ProfileScreen(navController: NavHostController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    var userEmail by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            currentUser?.let { User ->
                // Get email from Firebase Authentication
                userEmail = User.email ?: "No email"

                // Get additional data from Realtime Database
                val databaseRef = Firebase.database.reference
                val userRef = databaseRef.child("Users").child(User.uid)
                val snapshot = userRef.get().await()

                userName = snapshot.child("username").getValue(String::class.java) ?: "No name"
            } ?: run {
                errorMessage = "User not authenticated"
            }
        } catch (e: Exception) {
            errorMessage = "Error fetching data: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            errorMessage?.let {
                Text(text = it, color = Color.Red)
                return@Column
            }

            Text(text = "Profile Information", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Email: $userEmail")
            Text(text = "Name: $userName")
        }
    }
}

@Preview
@Composable
private fun ProfilePreview() {
    ProfileScreen(rememberNavController())

}