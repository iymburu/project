package com.example.project.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.models.User
import com.example.project.navigation.ROUTE_HOME
import com.example.project.ui.theme.screens.expense.AddExpenseScreen
import com.example.project.ui.theme.screens.home.Homescreen
import com.example.project.ui.theme.screens.login.Loginscreen
import com.example.project.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController= rememberNavController(),
               startDestination:String= ROUTE_HOME) {
    NavHost(navController = navController, modifier = modifier, startDestination = startDestination) {
        composable(ROUTE_LOGIN){
            Loginscreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_HOME){
            Homescreen(navController)
        }
        composable(ROUTE_ADD) {
            AddExpenseScreen(navController)
        }
    }

}