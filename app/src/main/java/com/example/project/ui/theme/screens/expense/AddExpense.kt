package com.example.project.ui.theme.screens.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.project.R
import com.example.project.ui.theme.myblue

@Composable
fun AddExpenseScreen(modifier: Modifier = Modifier) {
//    Surface(modifier = Modifier.fillMaxSize()) {
//        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//            val topBar = createRef()
//            Image(
//                painter = painterResource(id = R.drawable.bar), contentDescription = "bar",
//                modifier = Modifier.fillMaxWidth().constrainAs(topBar) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//        }
//    }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color= myblue)){
        Box(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(6.dp))) {
            Text("name")
        }
    }



}
@Preview
@Composable
private fun AddExpense_Preview() {
    AddExpenseScreen()

}