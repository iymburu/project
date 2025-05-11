package com.example.project.ui.theme.screens.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.data.TransactionModel
import com.example.project.models.Transaction
import com.example.project.navigation.ROUTE_CHART
import com.example.project.navigation.ROUTE_HOME
import com.example.project.navigation.ROUTE_UPDATE
import com.example.project.ui.theme.myblue
import com.example.project.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransactions(navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    var context = LocalContext.current
    var transactionRepository = TransactionModel(navController, context)

    var transactions = remember { mutableStateListOf<Transaction>() }
LaunchedEffect(Unit) {
    transactionRepository.viewTransaction(transactions)
}
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Transactions")
                }
            )
        }, bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {Column(modifier = Modifier
                .padding(start = 54.dp) ){
                Image(painter = painterResource(id=R.drawable.home), contentDescription = "home", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable{navController.navigate(
                    ROUTE_HOME
                )})}
                Spacer(modifier.width(60.dp))
                Column(modifier = Modifier
                     .padding(end = 54.dp) ){
                    Image(painter = painterResource(id=R.drawable.graph_24), contentDescription = "graph", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable { navController.navigate(
                        ROUTE_CHART) })
                }

            }
        }){ innerPadding ->
    LazyColumn(modifier= Modifier.padding(innerPadding))
    {

        items(transactions) { transaction ->
                        TransactionItem(
                            amount = transaction.amount,
                            type = transaction.type,
                            category = transaction.category,
                            date = transaction.date,
                            id = transaction.id,
                            navController = navController,
                            transactionRepository = transactionRepository

                        )

                    }}}




            }









@Composable
fun TransactionItem(amount:Double, type:String, category:String,date:String, id:String,
                    navController:NavHostController, transactionRepository:TransactionModel

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .clickable {},

        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()){
                Row { 
                    Text(text = category)
                    Text("              ")
                    Text(text = if(type=="income")" + $amount" else " - $amount",color=if(type=="income") Color.Green else Color.Red)
               }
                Text(text = date, modifier = Modifier.align(Alignment.CenterHorizontally))
               Row {
                   Button({transactionRepository.deleteProduct(id)}) {
                       Text("Delete")

                   }

               }
            }
            Column {

            }
            




        }
    }}

@Preview
@Composable
private fun VTrans() {
    ViewTransactions(rememberNavController())
    
}