package com.example.project.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.data.AuthViewModel
import com.example.project.data.TransactionModel
import com.example.project.models.User
import com.example.project.navigation.ROUTE_HOME
import com.example.project.ui.theme.screens.expense.AddExpenseScreen
import com.example.project.ui.theme.screens.expense.AddIncome
import com.example.project.ui.theme.screens.expense.ViewTransactions
 import com.example.project.ui.theme.screens.home.Homescreen
import com.example.project.ui.theme.screens.login.Loginscreen
import com.example.project.ui.theme.screens.profile.ProfileScreen
import com.example.project.ui.theme.screens.register.RegisterScreen
import com.example.project.ui.theme.screens.stats.TransactionPieChartScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController= rememberNavController(),
               startDestination:String= ROUTE_LOGIN) {
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
        composable(ROUTE_ADD_INCOME){
            AddIncome(navController)
        }
        composable(ROUTE_ADD) {
            AddExpenseScreen(navController)
        }
        composable(ROUTE_VIEW) {
            ViewTransactions(navController)
        }
        composable(ROUTE_CHART) {
            TransactionPieChartScreen(navController)
        }
        composable(ROUTE_PROFILE){
            ProfileScreen(navController)
        }

    }

}