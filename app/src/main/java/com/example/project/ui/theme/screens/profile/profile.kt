package com.example.project.ui.theme.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.data.AuthViewModel
import com.example.project.navigation.ROUTE_CHART
import com.example.project.navigation.ROUTE_HOME
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

    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize().background(color=Color.White)) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            errorMessage?.let {
                Text(text = it, color = Color.Red)
                return@Surface
            }
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (topBar, namerow, list, bottom) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_topbar),
                    contentDescription = "bar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(topBar) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                Box(
                    modifier = Modifier
                        .padding(top = 94.dp, start = 26.dp, end = 16.dp)
                        .fillMaxWidth()
                        .constrainAs(namerow) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                    Column(modifier = Modifier.align(Alignment.CenterStart)) {
                        Text(
                            " Profile " +
                                    "",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            fontStyle = FontStyle.Normal
                        )

                    }
                    Image(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "logout",
                        modifier = Modifier.align(
                            Alignment.TopEnd
                        ).clickable {
                            val authLog = AuthViewModel(navController, context)
                            authLog.logout()
                        })
                }
                Column(modifier = Modifier.background(color=Color.White).constrainAs(list) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.fillMaxWidth().padding(top = 65.dp, start = 20.dp, end = 30.dp).height(350.dp)) {
                    Text("Username :", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = userName, onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "account"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(

                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledBorderColor = Color.Black,
                            disabledPlaceholderColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Email address", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = userEmail, onValueChange = {},
                        readOnly = true,
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(

                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledBorderColor = Color.Black,
                            disabledPlaceholderColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )
                }
                Box(
                    modifier = Modifier
                    .constrainAs(bottom) {
                        top.linkTo(list.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 30.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(15.dp))
                )
                {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 54.dp)
                            .fillMaxWidth()
                            .height(54.dp)
                            .background(color = Color.Blue, shape = RoundedCornerShape(6.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 54.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "home",
                                colorFilter = ColorFilter.tint(color = Color.White),
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        ROUTE_HOME
                                    )
                                })
                        }
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 54.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.graph_24),
                                contentDescription = "graph",
                                colorFilter = ColorFilter.tint(color = Color.White),
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        ROUTE_CHART
                                    )
                                })
                        }
                    }


                }
            }
        }
    }
}

@Preview
@Composable
private fun Prof_prev() {
    ProfileScreen(rememberNavController())

}