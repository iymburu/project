package com.example.project.ui.theme.screens.stats

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.navigation.ROUTE_CHART
import com.example.project.navigation.ROUTE_HOME
import com.example.project.ui.theme.myblue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

@Composable
fun TransactionPieChartScreen(navController:NavHostController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    var income by remember { mutableStateOf(0f) }
    var expenses by remember { mutableStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            currentUser?.uid?.let { uid ->
                val database = Firebase.database
                val transactionsRef = database.getReference("Transactions")

                transactionsRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var totalIncome = 0f
                        var totalExpense = 0f

                        for (transactionSnapshot in snapshot.children) {
                            val amount = transactionSnapshot.child("amount").getValue(Float::class.java) ?: 0f
                            when (transactionSnapshot.child("type").getValue(String::class.java)?.lowercase()?.trim()) {
                                "income" -> totalIncome += amount
                                "expense" -> totalExpense += amount
                            }
                        }

                        income = totalIncome
                        expenses = totalExpense
                        isLoading = false
                    }

                    override fun onCancelled(error: DatabaseError) {
                        errorMessage = "Database error: ${error.message}"
                        isLoading = false
                    }
                })
            } ?: run {
                errorMessage = "User not authenticated"
                isLoading = false
            }
        } catch (e: Exception) {
            errorMessage = "Error: ${e.message}"
            isLoading = false
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val(topBar,chart,bottom)=createRefs()

        when {
            isLoading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center){
                CircularProgressIndicator()
                Text("Loading transactions...")
            }}
            errorMessage != null -> {
                Text(errorMessage!!, color = Color.Red)
            }
            income == 0f && expenses == 0f -> {
                Text("No transactions found")
            }
            else -> {
                Box(modifier = Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }) {
                Card(modifier = Modifier
                    .background(color = myblue)
                    .padding(45.dp)
                    .fillMaxWidth()) {
                    Row { Text("      Transaction Chart                    ", fontSize =30.sp, fontFamily = FontFamily.Cursive , modifier = Modifier.background(color = myblue),color = Color.White) }

                }}
                Spacer(modifier = Modifier.height(56.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.constrainAs(chart){
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.height(500.dp)) {
                    Spacer(modifier = Modifier.height(56.dp))
                PieChart(
                    income = income,
                    expenses = expenses,
                    modifier = Modifier.size(250.dp))
                            Spacer(modifier = Modifier.padding(16.dp))
                            TransactionLegend()
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text("Total Income: Kshs:${income.format()}")
                            Text("Total Expenses: Kshs:${expenses.format()}")}

                Box( modifier = Modifier
                    .constrainAs(bottom) {
                        top.linkTo(chart.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 60.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(15.dp))
                )
                {Spacer(modifier = Modifier.height(160.dp))
                    Box(modifier = Modifier
                    .padding(bottom = 54.dp)
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(6.dp))) {
                    Column(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 54.dp) ){
                        Image(painter = painterResource(id=R.drawable.home), contentDescription = "home", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable{navController.navigate(
                            ROUTE_HOME
                        )})}
                    Column(modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 54.dp) ){
                        Image(painter = painterResource(id=R.drawable.graph_24), contentDescription = "graph", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable { navController.navigate(
                            ROUTE_CHART) })
                    }}


                }

            }}
        }
    }
}

@Composable
fun PieChart(income: Float, expenses: Float, modifier: Modifier = Modifier) {
    val total = income + expenses
    val colors = listOf(Color(0xFF4CAF50), Color(0xFFF44336)) // Green, Red

    Canvas(modifier = modifier) {
        if (total == 0f) return@Canvas

        var startAngle = -90f // Start at top
        val size = size.minDimension

        // Draw Income
        val incomeSweep = (income / total) * 360
        drawPieSlice(
            color = colors[0],
            startAngle = startAngle,
            sweepAngle = incomeSweep,
            piesize = size
        )
        startAngle += incomeSweep

        // Draw Expense
        drawPieSlice(
            color = colors[1],
            startAngle = startAngle,
            sweepAngle = 360 - incomeSweep,
            piesize  = size
        )
    }
}

private fun DrawScope.drawPieSlice(
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
    piesize: Float
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true,
        size = Size(piesize, piesize),
        topLeft = Offset(
            (size.width - piesize) / 2,
            (size.height - piesize) / 2
        )
    )
}

@Composable
fun TransactionLegend() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LegendItem("Income", Color(0xFF4CAF50))
        LegendItem("Expenses", Color(0xFFF44336))
    }
}

@Composable
fun LegendItem(text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = text)
    }
}

private fun Float.format() = "%.2f".format(this)

@Preview
@Composable
private fun Piechart() {
    TransactionPieChartScreen(rememberNavController())

}