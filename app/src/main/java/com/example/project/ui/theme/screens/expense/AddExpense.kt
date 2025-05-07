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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.ui.theme.myblue
import java.nio.file.WatchEvent

@Composable
fun AddExpenseScreen(navController: NavHostController) {
    val menuExpanded by remember { mutableStateOf(false) }
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
    DropdownMenu(expanded = menuExpanded,
        onDismissRequest = {}) {
        DropdownMenuItem(
            text = {Text("Profile")},
            onClick = {/*TODO*/}
        )
        DropdownMenuItem(
            text={Text("Settings")},
            onClick = {/*TODO*/}
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)){
        val name = remember { mutableStateOf("") }
        val amount = remember { mutableStateOf(TextFieldValue("")) }
        val date = remember { mutableLongStateOf(0L) }
        val dateDialogVisibility by remember { mutableStateOf(false) }

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = myblue)
            .padding(horizontal = 10.dp, vertical = 64.dp)
        )
            {


            Row { Text("               Add Expense                           ", fontFamily = FontFamily.Cursive, fontSize = 30.sp,modifier = Modifier.background(color = myblue), color = Color.White)

                }
                Image(painter=painterResource(id= R.drawable.back_arrow), contentDescription = "menu",modifier= Modifier
                    .clickable { return@clickable }
                    .align(Alignment.TopStart)
                )

                Spacer(modifier= Modifier.width(90.dp))
                Image(painter=painterResource(id= R.drawable.menu_icon), contentDescription = "menu",modifier= Modifier
                    .clickable {/*TODO*/ }
                    .align(Alignment.TopEnd)
                )

        }


        Spacer(modifier= Modifier.height(70.dp))
        Card (modifier = Modifier
            .shadow(16.dp)
            .background(color = Color.White)
            .clip(shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
            .verticalScroll(rememberScrollState()),
            colors = CardDefaults.cardColors(containerColor = Color.White,

            )
        ) {
            Spacer(modifier = Modifier.size(4.dp))
            Text("Name", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.size(10.dp))
            ExpenseDropDown(listOf(
                "Groceries",
                "Rent",
                "Enjoyment",
                "Transport",
                "Shopping",
                "Gifts",
                "Other"
            ), onItemSelected = {
                name.value=it
            })
            Spacer(modifier = Modifier.height(20.dp))
            Text("Amount", fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(value = amount.value,
                onValueChange = {},
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = {text ->
                    val out ="Kshs" + text.text
                    val currencyOffsetTranslator = object : OffsetMapping{
                        override fun originalToTransformed(offset: Int): Int {
                            return offset + 1
                        }

                        override fun transformedToOriginal(offset: Int): Int {
                            return if(offset>0) offset - 1  else 0
                        }
                    }
                    TransformedText(AnnotatedString(out),currencyOffsetTranslator)
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) ,
                shape =  RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    disabledBorderColor = Color.Black,
                    disabledPlaceholderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,))


            Button({/*TODO*/}) {
                Text(" AddExpense ")

            }
        }

        }






}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDropDown(listOfItems: List<String>,onItemSelected:(item: String )-> Unit ) {
    var expanded = remember { mutableStateOf(false) }
    val ItemSelected = remember { mutableStateOf(listOfItems[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = it }
    ){
        OutlinedTextField(value = ItemSelected.value,
        onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            textStyle = TextStyle(color = Color.Black),
            readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded=expanded.value)},
           shape =  RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledBorderColor = Color.Black,
                disabledPlaceholderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            )
        )
        ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = {}) {
            listOfItems.forEach {
                DropdownMenuItem(text = {Text(text = it)}, onClick = {
                    ItemSelected.value=it
                    onItemSelected(ItemSelected.value)
                    expanded.value=false
                })
            }
        }
    }

}
@Preview
@Composable
private fun AddExpense_Preview() {
    AddExpenseScreen(rememberNavController()
)

}