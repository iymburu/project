package com.example.project.ui.theme.screens.expense

import android.R.attr.text
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)){
        val name by remember { mutableStateOf(TextFieldValue("")) }
        var amount by remember { mutableStateOf(TextFieldValue("")) }
        val date by remember { mutableLongStateOf(0L) }
        val dateDialogVisibility by remember { mutableStateOf(false) }




        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = myblue)
            .padding(64.dp)) {


            Row { Text("       Add Expense                        ", fontFamily = FontFamily.Cursive, fontSize = 30.sp,modifier = Modifier.background(color = myblue), color = Color.White)
                Image(painter=painterResource(id=R.drawable.menu_icon), contentDescription = "menu",modifier= Modifier
                    .clickable {/*TODO*/ }
                    )

                }

        }
        Spacer(modifier= Modifier.height(70.dp))
        Box(modifier = Modifier
            .shadow(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(6.dp))
            .padding(20.dp)
            .align(Alignment.CenterHorizontally)
            .verticalScroll(rememberScrollState())) {

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = amount,
                onValueChange = {amount=it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder ={Text("Enter amount")}
                )


        }
    }





}
@Preview
@Composable
private fun AddExpense_Preview() {
    AddExpenseScreen()

}