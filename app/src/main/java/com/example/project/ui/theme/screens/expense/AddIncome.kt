package com.example.project.ui.theme.screens.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.data.TransactionModel
import com.example.project.navigation.ROUTE_HOME
import com.example.project.navigation.ROUTE_VIEW
import com.example.project.ui.theme.myblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIncome(navController: NavHostController,
           ) {
    var context= LocalContext.current
    val menuExpanded = remember { mutableStateOf(false) }
//

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        var category by remember { mutableStateOf("") }
        var amount by remember { mutableStateOf("") }
        var type by remember { mutableStateOf("income") }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = myblue)
            .padding(horizontal = 10.dp, vertical = 64.dp)
        )
        {


            Row { Text("               Add Income                           ", fontFamily = FontFamily.Cursive, fontSize = 30.sp,modifier = Modifier.background(color = myblue), color = Color.White)

            }
        }
        DropdownMenu(expanded = menuExpanded.value,
            onDismissRequest = {menuExpanded.value= false}) {
            DropdownMenuItem(
                text = {Text("Profile")},
                onClick = {/*TODO*/}
            )
            DropdownMenuItem(
                text={Text("Settings")},
                onClick = {/*TODO*/}
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
            Text("Category",  fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(value = category,
                onValueChange = {category=it},
                modifier = Modifier.fillMaxWidth(),
                shape =  RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    disabledBorderColor = Color.Black,
                    disabledPlaceholderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,))
            Spacer(modifier = Modifier.height(20.dp))
            Text("Amount", fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(value = amount,
                onValueChange = {amount=it},

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
            Spacer(modifier = Modifier.height(20.dp))
            Text("Date", fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Select date") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .verticalScroll(rememberScrollState()),
                shape =  RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    disabledBorderColor = Color.Black,
                    disabledPlaceholderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black)

            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .offset(y = 23.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(13.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button({ var transactionRepository = TransactionModel(navController, context = context)
                transactionRepository.saveTransaction(category.toString(), amount.toDouble(),type,selectedDate)
                navController.navigate(ROUTE_VIEW)
            }

            , modifier = Modifier.fillMaxWidth()) {
                Text(" Add Income")

            }
        }

    }






}
    



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeDropDown(listOfItems: List<String>,onItemSelected:(item: String )-> Unit ) {
    var expanded = remember { mutableStateOf(false) }
    val ItemSelected = remember { mutableStateOf(listOfItems[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = it }
    ) {
        OutlinedTextField(
            value = ItemSelected.value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            textStyle = TextStyle(color = Color.Black),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
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
        ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = {}) {
            listOfItems.forEach {
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    ItemSelected.value = it
                    onItemSelected(ItemSelected.value)
                    expanded.value = false
                })
            }
        }
    }
}

@Preview
@Composable
private fun Income() {
    AddIncome(rememberNavController())
}
