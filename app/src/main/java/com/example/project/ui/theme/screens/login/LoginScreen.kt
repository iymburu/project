package com.example.project.ui.theme.screens.login

import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.navigation.ROUTE_REGISTER
import com.example.project.ui.theme.PurpleGrey80
import com.example.project.ui.theme.myblue
import java.nio.file.WatchEvent

@Composable
fun Loginscreen(navController: NavHostController) {



    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color= myblue)){
        Card(modifier = Modifier
            .background(color = Color.White)
            .padding(45.dp)
            .fillMaxWidth()) {
            Row(modifier = Modifier.background(color = Color.White)) { Text("      Welcome Back !              ", fontSize = 30.sp, modifier = Modifier.background(color = Color.White),color = Color.Black) }
            Row { Text("                 Login                           ", fontFamily = FontFamily.Cursive, fontSize = 26.sp,modifier = Modifier.background(color = Color.White), color = Color.Black) }
        }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var pass by remember { mutableStateOf(TextFieldValue("")) }

        Spacer(modifier = Modifier.height(120.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your email", color = Color.White) },
            leadingIcon = {Icon(Icons.Default.Email, contentDescription = "email")},

            )
        Spacer(modifier= Modifier.height(23.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Enter your password ", color = Color.White) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) }
        )
        Spacer(modifier= Modifier.height(20.dp))
        Button({/*TODO*/},modifier= Modifier.width(300.dp),
           colors=ButtonDefaults.buttonColors(Color.Blue) ) {
            Text("Login",
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = FontFamily.Cursive) }
        Spacer(modifier = Modifier.height(20.dp))


    TextButton({navController.navigate(ROUTE_REGISTER)}) { Text("Don't have an account?Sign up", color = Color.White) }







    }}






@Preview
@Composable
private fun Login_Preview() {
    Loginscreen(rememberNavController())

}