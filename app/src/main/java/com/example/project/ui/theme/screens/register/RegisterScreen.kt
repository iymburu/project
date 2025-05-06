package com.example.project.ui.theme.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.AuthViewModel
import com.example.project.navigation.ROUTE_LOGIN
import com.example.project.ui.theme.Pink40
import com.example.project.ui.theme.Pink80
import com.example.project.ui.theme.myblue
import com.google.firebase.database.core.Context

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .verticalScroll(rememberScrollState())){
        Card(modifier = Modifier
            .background(color = myblue)
            .padding(45.dp)
            .fillMaxWidth()) {
            Row { Text("          Signup here                      ", fontFamily = FontFamily.Cursive, fontSize = 30.sp,modifier = Modifier.background(color = myblue), color = Color.White) }
        }
        Spacer(modifier = Modifier.height(50.dp))
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var pass by remember { mutableStateOf(TextFieldValue("")) }
        var confpass by remember { mutableStateOf(TextFieldValue("")) }
        var username by remember { mutableStateOf(TextFieldValue("")) }
        var context = LocalContext.current
        OutlinedTextField(

            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your email", color = Color.DarkGray) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "email") },

                
            )


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(

            value =username,
            onValueChange = { username = it },
            label = { Text("Enter your username", color = Color.DarkGray) },
            leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = "username") },


            )


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Enter your password", color = Color.DarkGray) },
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "pass")},

            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = confpass,
            onValueChange = { confpass = it },
            label = { Text("Confirm your password", color = Color.DarkGray) },
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "confpass")},

            )
        Spacer(modifier = Modifier.height(20.dp))
        Button({val myregister=AuthViewModel(navController,context )
               myregister.Signup(email.text.trim(),username.text.trim(),pass.text.trim(),confpass.text.trim())},
            modifier=Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text("Register",
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = FontFamily.Cursive) }
        Spacer(modifier = Modifier.height(20.dp))

        TextButton({navController.navigate("login")}) { Text("Already have an account?Login", color = Color.DarkGray) }
    }
}






@Preview
@Composable
private fun Register_Preview() {
    RegisterScreen(rememberNavController())

}